package handler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import db.AlbumDBBean;
import db.AlbumDataBean;
import db.CmtDBBean;
import db.CmtDataBean;
import db.CoordDBBean;
import db.CoordDataBean;
import db.LogDBBean;
import db.MemberDBBean;
import db.MemberDataBean;
import db.TagDBBean;
import db.TagDataBean;
import db.BoardDBBean;
import db.BoardDataBean;
import db.TripDBBean;
import db.TripDataBean;
import db.UserDBBean;
import db.UserDataBean;

@Controller
public class SvcProHandler {
	private static final int ADMIN = 9;
	private static final int EXTENSION_ERROR = -1;
	private static final int SIZE_ERROR = -2;
	private static final int SUCCESS = 1;
	private static final int LIMIT_SIZE = 5 * 1024 * 1024;// image max size=5M;

	@Resource
	private TripDBBean tripDao;
	@Resource
	private AlbumDBBean albumDao;
	@Resource
	private CmtDBBean cmtDao;
	@Resource
	private CoordDBBean coordDao;
	@Resource
	private TagDBBean tagDao;
	@Resource
	private UserDBBean userDao;
	@Resource
	private BoardDBBean boardDao;
	@Resource
	private MemberDBBean memberDao; 


	///////////////////////////////// user pages/////////////////////////////////

	@RequestMapping("/regPro")
	public ModelAndView UserInputProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] tag_id = request.getParameterValues("user_tag");
		UserDataBean userDto = new UserDataBean();
		userDto.setUser_id(request.getParameter("user_id"));
		userDto.setPasswd(request.getParameter("passwd"));
		userDto.setUser_name(request.getParameter("user_name"));
		userDto.setEmail(request.getParameter("email1"));
		// user_level
		// gender
		int gender = Integer.parseInt(request.getParameter("gender"));

		userDto.setGender(gender);
	
		userDto.setReg_date(new Timestamp(System.currentTimeMillis()));

		int result = userDao.insertUser(userDto);
		
		if(tag_id !=null) {
			for(String tag:tag_id) {
		        Map<String, String> map = new HashMap<>(); 
		        map.put("user_id", request.getParameter("user_id"));
		        map.put("tag_id", tag);
		        userDao.insertUser_tag(map);
			}
		}
		
		request.setAttribute("result", result);
		request.setAttribute("userDto", userDto);
		request.setAttribute("user_id", userDto.getUser_id());

		return new ModelAndView("svc/regPro");
	}

	@RequestMapping("/userModPro")
	public ModelAndView UserModifyprocess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserDataBean userDto = new UserDataBean();
		String user_id = (String) request.getSession().getAttribute("user_id");
		userDto.setUser_id(user_id);
		userDto.setPasswd(request.getParameter("passwd"));
		userDto.setUser_name(request.getParameter("user_name"));
		List<TagDataBean> userTags = new ArrayList<TagDataBean>();
		
		try {
			String[] tagValues = request.getParameterValues("tags");		
			for(int i=0; i<tagValues.length; i++) {
				TagDataBean tempTagBean = tagDao.getTag(Integer.parseInt(tagValues[i]));
				userTags.add(tempTagBean);		
			}
			int result = userDao.modifyUser(userDto);
			if (result == 1) {
				request.setAttribute("result", result);
				result = tagDao.updateUserTags(user_id, userTags);
			}			
		}catch(NullPointerException e) {
			int result = userDao.modifyUser(userDto);
			if (result == 1) {
				request.setAttribute("result", result);
			}			
		}		
		return new ModelAndView("svc/userModPro");
	}

	@RequestMapping("/loginPro")
	public ModelAndView Loginprocess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int userType = 1;
		String id = request.getParameter("user_id");
		String passwd = request.getParameter("passwd");
		UserDataBean userDto = userDao.getUser(id);
		int result = 0;

		try {
			if(passwd.equals(userDto.getPasswd())){
				result = 1;
			} else {
				if(userDto.getUser_id().equals(id)) {				//	nullpoint가 안날경우를 대비한 방어용 코드.
					result =-1;
				} 
			}
			
			if (result == 1) {
				int user_level = userDto.getUser_level();
				if (user_level == ADMIN) {
					userType = 9;									
				}
				request.setAttribute("userType", userType);
			}
			if (result != -1) {
				request.setAttribute("userDto", userDto);
			}
		} catch(NullPointerException e) {	//만약 아이디가 없을경우 바로 이쪽으로 이동.
			
		} 
		
		request.setAttribute("result", result);
		request.setAttribute("id", id);
		
		return new ModelAndView("svc/loginPro");
		
	}

	@RequestMapping("/logout") // logout 
	public ModelAndView LogoutProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		request.getSession().removeAttribute("user_id");
		request.getSession().removeAttribute("user_level");
		// send user to main page
		// but we don't have a main page yet, so send him to board list, temporary
		return new ModelAndView("svc/login");
	}

	@RequestMapping("/userLeavePro")
	public ModelAndView DeleteProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		String id = (String) request.getSession().getAttribute("user_id");
		String passwd = request.getParameter("passwd");
		UserDataBean userDto = userDao.getUser(id);
		int resultCheck = 0;
		if(userDto.getPasswd().equals(passwd)) {
			resultCheck = 1;
		}
		request.setAttribute("resultCheck", resultCheck);

		if (resultCheck == 1) {
			int result = userDao.deleteUser(id);
			request.setAttribute("result", result);
		}

		return new ModelAndView("svc/userLeavePro");
	}

	//// Email
	@RequestMapping("/email")
	public ModelAndView EmailCheckProcess(HttpServletRequest request, HttpServletResponse response) {
		String host = "smtp.gmail.com"; // smtp 서버
		String subject = "EmailCheck"; // 보내는 제목 설정
		String fromName = "Admin"; // 보내는 이름 설정
		String from = "show112924@gmail.com"; // 보내는 사람(구글계정)
		int etype = Integer.parseInt(request.getParameter("eType"));
		String content ="";
		String email = request.getParameter("email1");
		boolean sendCk = false;
		int result = -1;
			if(etype == 0) {
				String authNum = authNum(); // 인증번호 위한 난수 발생부분
				content = "Travlers : 당신의 인증번호는 [" + authNum + "] 입니다"; 
				request.setAttribute("authNum", authNum);
				sendCk = true;
				result = 0;
			} else {
				result = userDao.EmailCheck(email);
				request.setAttribute("email", email);
				sendCk = true;
				if(result == 1 ) {
					if(etype==1) {	//	아이디 찾기 일 경우
						UserDataBean userDto = userDao.getUserEmailId(email);
						content = "당신의 아이디는 [" + userDto.getUser_id() + "]입니다"; 
					} else if(etype==2) {	//비밀번호 찾기 일 경우
						UserDataBean userDto = userDao.getUserEmailPasswd(email);
						content = "당신의 비밀번호는 [" + userDto.getPasswd() + "]입니다"; 
					}
				} 
			}
			if(sendCk = true) {
				request.setAttribute("result", result);
				try {
					Properties props = new Properties();
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.transport.protocol", "smtp");
					props.put("mail.smtp.host", host);
					props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					props.put("mail.smtp.port", "465");
					props.put("mail.smtp.user", from);
					props.put("mail.smtp.auth", "true");
		
					Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("show112924@gmail.com", "208795a!");
						}
					});
		
					Message msg = new MimeMessage(mailSession);
					InternetAddress[] address = { new InternetAddress(email) };
					msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "utf-8", "B")));
					msg.setRecipients(Message.RecipientType.TO, address);
					msg.setSubject(subject);
					msg.setSentDate(new java.util.Date());
					msg.setContent(content, "text/html; charset=utf-8");
		
					Transport.send(msg);
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				if(etype==1) { //형태에 따른 리턴 위치 처리.
					return new ModelAndView("svc/EmailIdPro");	
				} else if (etype==2) {
					return new ModelAndView("svc/EmailPasswdPro"); 
				} else {
					return new ModelAndView("svc/emailCheck");
				}
		}
		
		return new ModelAndView("svc/emailCheck");
	}
	
	public static String authNum() { // 난수발생부분
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i <= 4; i++) {
			int num = (int) (Math.random() * 9 + 1);
			buffer.append(num);
		}
		return buffer.toString();
	}

	///////////////////////////////// board pages/////////////////////////////////
	@RequestMapping("/tripWritePro")
	public ModelAndView svcTripWriteProProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		int schedulenum=Integer.parseInt(request.getParameter("schedulenum"));//일정개수
		//insert gg_trip_board 
		String user_id = (String) (request.getSession().getAttribute("user_id"));
		
		BoardDataBean boardDto = new BoardDataBean();

		boardDto.setUser_id(user_id);
		boardDto.setBoard_title(request.getParameter("trip_title"));
		boardDto.setBoard_content(request.getParameter("content"));
		boardDto.setBoard_contact(request.getParameter("board_contact"));
		boardDao.insertBoard_no(boardDto);
		
		int board_no = boardDto.getBoard_no();// board_no
		request.setAttribute("board_no", board_no);

		CoordDataBean coordDto = new CoordDataBean();
		for (int i = 1; i <= schedulenum; i++) {
			TripDataBean tripDto = new TripDataBean();
			String coord_name = request.getParameter("place"+i);
			System.out.println(coord_name);
			List<CoordDataBean> coords = coordDao.checkCoordName(coord_name);	//	 같은이름의 coord가 있나 체크.
			int coord_id=-1;	//	coord_id 초기값.
			
			if(coords.size() > 0) {	//검색했는데 같은이름의 검색경로가 있으면 이미 있는 놈의 coord_id를 씀.
				coord_id = coords.get(0).getCoord_id();
				tripDto.setCoord_id(coord_id);//	tripDto에 대입
			} else {		// 없으면!
				String country_code = request.getParameter("country_code" + i + "");
				double coord_lat = Double.parseDouble(request.getParameter("lat" + i + ""));
				double coord_long = Double.parseDouble(request.getParameter("lng" + i + ""));
				
				coordDto.setCoord_name(coord_name);
				coordDto.setCountry_id(country_code);
				coordDto.setCoord_lat(coord_lat);
				coordDto.setCoord_long(coord_long);
				
				coordDao.insertCoord(coordDto);// locDto의 coord_id에 좌표값 저장한 후 생성된 coord_id저장 됨
				coord_id = coordDto.getCoord_id();
			}
			
			tripDto.setCoord_id(coord_id);
			tripDto.setBoard_no(board_no);
			
			String [] start = request.getParameter("start"+i).split("/");
			LocalDate ldt = LocalDate.of(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2]));
			Date start_date = new Date(ldt.toEpochDay());
		
			String [] end = request.getParameter("end"+i).split("/");
			ldt = LocalDate.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2]));
			Date end_date = new Date(ldt.toEpochDay());
			//start , end Date타입으로 변환. 확인 필수!
			
			tripDto.setStart_date(start_date);
			tripDto.setEnd_date(end_date);
			tripDto.setTrip_member_count(Integer.parseInt(request.getParameter("trip_member_count"+i)));

			tripDao.insertTrip(tripDto);
			int trip_id = tripDto.getTrip_id();
			
			MemberDataBean memberDto = new MemberDataBean();
			memberDto.setUser_id(user_id);
			memberDto.setTrip_id(trip_id);
			memberDao.addTripMember(memberDto);

			}

		// get tags
		String[] tags = request.getParameterValues("tag");
		if (tags != null) {// tag를 선택한 경우에만 실행
			// put them in a Map and call db update
			Map<String, Integer> tagSetter = new HashMap<String, Integer>();
			for (String tag : tags) {
				tagSetter.put("board_no", board_no);
				tagSetter.put("tag_id", Integer.parseInt(tag));
				tagDao.setTripTag(tagSetter);	//	FIXME: 태그 후일 정리 부분.
			}
		}
		
		return new ModelAndView("svc/tripWritePro");
	}

	@RequestMapping("/tripModPro")
	public ModelAndView svcTrpModProProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		BoardDataBean boardDto = new BoardDataBean();
		boardDto.setBoard_no(board_no);
		boardDto.setBoard_title(request.getParameter("trip_title"));
		boardDto.setBoard_content(request.getParameter("content"));
		boardDto.setBoard_contact(request.getParameter("board_contact"));
		
		
		String[] tagValues=request.getParameterValues("tags");
		//match tag_id & tag_value 
		List<TagDataBean> tripTags=new ArrayList<TagDataBean>();	
		for(int i=0; i<tagValues.length; i++) {
			TagDataBean tempTagBean=new TagDataBean();
			tempTagBean.setTag_id(Integer.parseInt(tagValues[i]));
			tripTags.add(tempTagBean);
		}
		
		//update modified "tripMod" in DB
		int result = boardDao.updateBoard(boardDto);
		request.setAttribute("result", result);
		request.setAttribute("board_no", board_no);
		tagDao.updateTripTags(board_no, tripTags);
		
		return new ModelAndView("svc/tripModPro");
	}

	// if fail to delete, we should show user an alert
	// so we need this result parameter
	@RequestMapping("/tripDelPro")
	public ModelAndView svcTripDelProProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int result = boardDao.deletePost(board_no);
		request.setAttribute("result", result);
		return new ModelAndView("svc/tripDel");
	}

	///////////////////////////////// album pages/////////////////////////////////

	@RequestMapping("/boardAlbumPro.go")
	public ModelAndView svcAlbumProProcess(HttpServletRequest request, MultipartHttpServletRequest mtrequest)
			throws HandlerException {

		String uploadPath = request.getServletContext().getRealPath("/");
		System.out.println("path:"+uploadPath);
		String path = uploadPath + "save/";
		String DBpath = request.getContextPath() + "/save/";

		new File(path).mkdir();

		List<MultipartFile> fileList = mtrequest.getFiles("files");
		// 모든 파일 선택 가능->추후 사진 파일만 선택 할 필요 있음
		AlbumDataBean albumDto;

		int fileResult = SUCCESS;// 파일 입출력 결과
		long imgSize;// 이미지 파일 크기
		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명

			if (!isValidExtension(originFileName)) {
				fileResult = EXTENSION_ERROR;
				break;
			}

			imgSize = mf.getSize();
			if (imgSize >= LIMIT_SIZE) {
				fileResult = SIZE_ERROR;
				break;
			}
			originFileName = System.currentTimeMillis() + getRandomString()
					+ originFileName.substring(originFileName.lastIndexOf(".")).toLowerCase();
			String safeFile = path + originFileName;
			String safeDBFile = DBpath + originFileName;
			
			try {
				mf.transferTo(new File(safeFile));
				// db insert
				albumDto = new AlbumDataBean();
				albumDto.setPhoto_url(safeDBFile);
				int trip_id=Integer.parseInt(request.getParameter("trip_id"));
				albumDto.setTrip_id(trip_id);
				String user_id=(String)request.getSession().getAttribute("user_id");
				albumDto.setUser_id(user_id);
				int board_no=Integer.parseInt(request.getParameter("board_no"));
				request.setAttribute("board_no", board_no);
				albumDao.addPhoto(albumDto);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("fileResult", fileResult);
		return new ModelAndView("/svc/boardAlbumPro");
	}

	@RequestMapping("/photoDel")	
	public ModelAndView svcPhotoDelProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		request.setAttribute("board_no", board_no);
		int trip_id = Integer.parseInt(request.getParameter("trip_id"));
		request.setAttribute("trip_id", trip_id);
		int photo_id = Integer.parseInt(request.getParameter("photo_id"));
		int result = albumDao.delPhoto(photo_id);
		request.setAttribute("result", result);
		return new ModelAndView("redirect:trip.go?board_no="+board_no);
	}

	@RequestMapping("/downloadAlbum")
	public void downloadAlbumProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException, IOException {
		int trip_id = Integer.parseInt(request.getParameter("trip_id"));
		List<String> photo_urls = albumDao.getPhoto_urls(trip_id);

		String realFolder = request.getServletContext().getRealPath("/") + "save/";
		int bufferSize = LIMIT_SIZE;
		ZipOutputStream zos = null;
		String zipName = "Travelers_Album" + trip_id;

		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=" + zipName + ".zip" + ";");

		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream os = response.getOutputStream();
		zos = new ZipOutputStream(os); // ZipOutputStream
		zos.setLevel(8); // 압축 레벨 - 최대 압축률은 9, 디폴트 8
		BufferedInputStream bis = null;

		for (String photo_url : photo_urls) {
			String path[] = photo_url.split("/");
			String fileName = path[path.length - 1];
			String filePath = realFolder + fileName;

			File sourceFile = new File(filePath);

			bis = new BufferedInputStream(new FileInputStream(sourceFile));
			ZipEntry zentry = new ZipEntry(fileName);
			zentry.setTime(sourceFile.lastModified());
			zos.putNextEntry(zentry);

			byte[] buffer = new byte[bufferSize];

			int cnt = 0;
			while ((cnt = bis.read(buffer, 0, bufferSize)) != -1) {
				zos.write(buffer, 0, cnt);
			}
			zos.closeEntry();
		}
		zos.close();
		bis.close();
	}

	@RequestMapping("/download")
	public void downloadProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException, IOException {
		int n = Integer.parseInt(request.getParameter("num"));
		response.reset();
		if (n == 1) {
			downloadImgProcess(request, response);
		} else {
			String realFolder = request.getServletContext().getRealPath("/") + "save/";
			int bufferSize = LIMIT_SIZE;
			ZipOutputStream zos = null;
			String zipName = "Travelers_Photos";
			response.setHeader("Content-Disposition", "attachment; filename=" + zipName + ".zip" + ";");

			response.setHeader("Content-Transfer-Encoding", "binary");
			OutputStream os = response.getOutputStream();
			zos = new ZipOutputStream(os); // ZipOutputStream
			zos.setLevel(8); // 압축 레벨 - 최대 압축률은 9, 디폴트 8
			BufferedInputStream bis = null;
			for (int i = 0; i < n; i++) {
				String path[] = request.getParameter("photo" + i).split("/");
				String fileName = path[path.length - 1];
				String filePath = realFolder + fileName;

				File sourceFile = new File(filePath);

				bis = new BufferedInputStream(new FileInputStream(sourceFile));
				ZipEntry zentry = new ZipEntry(fileName);
				zentry.setTime(sourceFile.lastModified());
				zos.putNextEntry(zentry);

				byte[] buffer = new byte[bufferSize];

				int cnt = 0;

				while ((cnt = bis.read(buffer, 0, bufferSize)) != -1) {
					zos.write(buffer, 0, cnt);
				}
				zos.closeEntry();
			}
			zos.close();
			bis.close();
		}
	}

	// download할 img가 한개인 경우-download one image
	public void downloadImgProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException, IOException {
		String realFolder = request.getServletContext().getRealPath("/") + "save/";
		String path[] = request.getParameter("photo0").split("/");
		String fileName = path[path.length - 1];
		String filePath = realFolder + fileName;

		byte fileByte[] = FileUtils.readFileToByteArray(new File(filePath));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	///////////////////////////////// ajax list/////////////////////////////////

	@RequestMapping(value = "/checkId.go", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<Object, Object> idCheck(@RequestBody String user_id) {
		user_id = user_id.split("=")[0];	//TODO: 체크필요 파싱안해도 될거같은디?;
		int countId = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();

		countId = userDao.checkId(user_id);
		map.put("countId", countId);

		return map;
	}

	@RequestMapping(value = "/nameCheck.go", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<Object, Object> nameCheck(@RequestBody String user_name) throws UnsupportedEncodingException {
		user_name = URLDecoder.decode(user_name,"UTF-8");
		user_name = user_name.split("=")[0];
		int countName = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();

		countName = userDao.nameCheck(user_name);
		map.put("countName", countName);

		return map;
	}

	@RequestMapping(value = "/commentInsert.go", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void commentInsertProcess(HttpServletRequest request, HttpSession session) throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String user_id=request.getParameter("user_id");
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		String comment_content=request.getParameter("c_content");
		CmtDataBean cmtDto = new CmtDataBean();
		if(comment_content!=null) {
			cmtDto.setUser_id(user_id);
			cmtDto.setBoard_no(board_no);
			cmtDto.setComment_content(comment_content);
			cmtDao.insertComment(cmtDto);
		}
	}

	@RequestMapping(value = "/commentSelect.go", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<CmtDataBean> commentSelectProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {

		int board_no = Integer.parseInt(request.getParameter("board_no"));
		List<CmtDataBean> comment = cmtDao.getComment(board_no);
		for (CmtDataBean cmtDto : comment) {
			String user_name;
			String user_id = cmtDto.getUser_id();
			if (user_id == null || user_id.equals("")) {
				user_name = "Ex-User";
				cmtDto.setUser_name(user_name);
			} else {
				user_name = memberDao.getUserName(user_id);
				cmtDto.setUser_name(user_name);
			}
		}

		request.setAttribute("comment", comment);
		// don't set the name of variable like this!
		return comment;
	}

	@RequestMapping(value = "/commentUpdate.go", method = RequestMethod.POST, produces = "application/json") // 댓글 수정
	@ResponseBody
	private void commentUpdateProcess(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		CmtDataBean cmtDto = new CmtDataBean();
		cmtDto.setComment_id(Integer.parseInt(request.getParameter("comment_id")));
		cmtDto.setComment_content(request.getParameter("comment_content"));
		cmtDao.updateComment(cmtDto);
	}

	@RequestMapping(value = "/commentDelete.go", method = RequestMethod.POST) // 댓글 삭제
	@ResponseBody
	private void commentDeleteProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int comment_id = Integer.parseInt(request.getParameter("comment_id"));
		cmtDao.deleteComment(comment_id);
	}

	@RequestMapping(value = "/memberAttend.go", method = RequestMethod.POST)
	@ResponseBody
	private List<MemberDataBean> memberAttendProcess(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int trip_id = Integer.parseInt(request.getParameter("trip_id"));
		String user_id = request.getParameter("user_id");
		MemberDataBean memberDto = new MemberDataBean();		
		memberDto.setTrip_id(trip_id);
		memberDto.setUser_id(user_id);

		boolean memberCheck = memberDao.isTripMember(memberDto);

		if (!memberCheck) {
			int addMemberResult = memberDao.addTripMember(memberDto);
			request.setAttribute("addMemberResult", addMemberResult);
			if(addMemberResult>=1) request.setAttribute("isMember", true);
			else request.setAttribute("isMember", false);
		}

		List<MemberDataBean> memberList = memberDao.getMembers(trip_id);
		return memberList;
	}

	@RequestMapping(value = "/memberAbsent.go", method = RequestMethod.POST)
	@ResponseBody
	private List<MemberDataBean> memberAbsentProcess(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int trip_id = Integer.parseInt(request.getParameter("trip_id"));
		String user_id = request.getParameter("user_id");
	    
	    MemberDataBean memberDto = new MemberDataBean();
	    memberDto.setTrip_id(trip_id);
	    memberDto.setUser_id(user_id);

	    boolean memberCheck = memberDao.isTripMember(memberDto);

		if (memberCheck) {
			int delMemberResult = memberDao.delTripMember(memberDto);
			request.setAttribute("delMemberResult", delMemberResult);
			if (delMemberResult>=1) request.setAttribute("isMember", false);
			else request.setAttribute("isMember", true);
		}
		List<MemberDataBean> memberList = memberDao.getMembers(trip_id);
		return memberList;
	}
	//////////////////////////////////////ajax추가분 이민재 2018.11.15 /////////////////////////////////////
	@RequestMapping(value = "searchTag", produces="application/json") 
	@ResponseBody
	private JSONArray searchTag(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tag_value = request.getParameter("tag_value");
		List<TagDataBean> tags = tagDao.tagAutoComplete(tag_value);
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		String tagstr=""; 
		try { 
			tagstr = mapper.writeValueAsString(tags);
			
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		JSONParser parser = new JSONParser();
		Object parseobj = parser.parse(tagstr);
		JSONArray jsonTag = (JSONArray) parseobj;
		return jsonTag;
	}
	
	@RequestMapping(value = "insertUserTag", produces="application/json") // 태그 검색후 없으면 추가하는 메소드.
	@ResponseBody
	private JSONObject insertUserTag(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tag_value = request.getParameter("tag_value");
		int ckcnt = tagDao.checkTag(tag_value);
		TagDataBean tagDto = new TagDataBean();
		if(ckcnt < 1 ) {
			tagDto.setTag_value(tag_value);
			tagDao.insertUserTag(tagDto);
		} else {
			tagDto = tagDao.getTagByValue(tag_value);
		}
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		String tagstr=""; 
		try { 
			tagstr = mapper.writeValueAsString(tagDto);
			
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		JSONParser parser = new JSONParser();
		Object parseobj = parser.parse(tagstr);
		JSONObject jsonobj = (JSONObject) parseobj;
		
		return jsonobj;
	}
	
	@RequestMapping(value = "searchCoord", produces="application/json") 
	@ResponseBody
	private JSONArray searchCoord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String coord_name = request.getParameter("coord_name");
		List<CoordDataBean> coords = coordDao.coordAutoComplete(coord_name);
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		String coordstr=""; 
		try { 
			coordstr = mapper.writeValueAsString(coords);
			
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		JSONParser parser = new JSONParser();
		Object parseobj = parser.parse(coordstr);
		JSONArray jsonCoord = (JSONArray) parseobj;
		return jsonCoord;
	}
	//////////////////////////////////////ajax추가분 이민재 2018.11.15 /////////////////////////////////////
	///////////////////////////////// etc/////////////////////////////////
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	private boolean isValidExtension(String originFileName) {
		String fileExtension = originFileName.substring(originFileName.lastIndexOf(".") + 1).toLowerCase();
		switch (fileExtension) {
		case "jpg":
		case "png":
		case "gif":
			return true;
		}
		return false;
	}
	
	@RequestMapping("/makeLog")	
	public ModelAndView makeLog(HttpServletRequest request, HttpServletResponse response)
			throws HandlerException, ParseException, IOException {
		LogDBBean logDao = new LogDBBean();
		JSONArray jsonPosts = logDao.makeMemberLog();
		request.setAttribute("json", jsonPosts);
		return new ModelAndView("googleAPI/makeLog");
	}

}