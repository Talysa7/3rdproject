package handler;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import java.util.Comparator;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import db.AlbumDBBean;
import db.AlbumDataBean;
import db.BoardDataBean;
import db.CmtDBBean;

import db.CoordDBBean;
import db.CoordDataBean;
import db.CoordReviewDBBean;
import db.CoordReviewDataBean;
import db.CountryDBBean;

import db.LogDBBean;
import db.MemberDBBean;
import db.MemberDataBean;
import db.PredictDBBean;
import db.PredictDataBean;

import db.ReviewDBBean;
import db.ReviewDataBean;
import db.TagDBBean;
import db.TagDataBean;
import db.BoardDBBean;
import db.TripDBBean;
import db.TripDataBean;
import db.UserDBBean;
import db.UserDataBean;

@Controller
public class SvcViewHandler {
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
	@Resource
	private ReviewDBBean reviewDao;
	@Resource
	private CoordReviewDBBean coordReviewDao;
	@Resource
	private LogDBBean logDao;
	@Resource
	private CountryDBBean countryDao;
	@Resource
	private PredictDBBean predictDao;

	//amount of displayed photos in a page
	private static final int photoPerPage=6;
	//??? -talysa7
	private static final String MAP="0";
	//How many posts do we need in a page, default is 10
	private static final int postPerPage=10;

	/////////////////////////////////default pages/////////////////////////////////

	@RequestMapping("/*")
	public ModelAndView svcDefaultProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/default");
	}

	/////////////////////////////////main page/////////////////////////////////

	//temporary go to login
	@RequestMapping("/main")
	public ModelAndView svcMainProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/login");
	}

	//view page of after clustering
	@RequestMapping("/reportView")
	public ModelAndView reportViewProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> coordNB = new ArrayList<>();
		
		try { 
			Scanner scanner1 = new Scanner(new File("c:\\coordCount.csv")); 
			scanner1.useDelimiter(System.getProperty("line.separator")); 
			while(scanner1.hasNext()) 
				coordNB.add(scanner1.next());
			scanner1.close(); 
		} catch(FileNotFoundException e) { 
			e.printStackTrace(); 
		}
		
		String[] afterReadCoord = coordNB.toArray(new String[coordNB.size()]);
		
		String[] temporarySplit = new String[2];
		String[] coordNameNum = new String[11];
		String[] coordCountNum = new String[11];
		
		for(int i=1; i<11; i++) {
			temporarySplit = afterReadCoord[i].split(",");
			coordNameNum[i] = temporarySplit[0];
			coordCountNum[i] = temporarySplit[1];
		}
		
		coordNameNum[0] = "0"; // null 방지로 임의의 값 대입
		
		int[] coordNameNumInt = Arrays.asList(coordNameNum).stream().mapToInt(Integer::parseInt).toArray();
		
		Map<String,Integer> sendNum = new HashMap<String, Integer>(); 
		
		for(int i=1; i<11; i++) {
			sendNum.put("coord_id"+i, coordNameNumInt[i]);
		}

		String convertRst1 = coordDao.coordIdtoCoordName1(sendNum);
		String convertRst2 = coordDao.coordIdtoCoordName2(sendNum);
		String convertRst3 = coordDao.coordIdtoCoordName3(sendNum);
		String convertRst4 = coordDao.coordIdtoCoordName4(sendNum);
		String convertRst5 = coordDao.coordIdtoCoordName5(sendNum);
		String convertRst6 = coordDao.coordIdtoCoordName6(sendNum);
		String convertRst7 = coordDao.coordIdtoCoordName7(sendNum);
		String convertRst8 = coordDao.coordIdtoCoordName8(sendNum);
		String convertRst9 = coordDao.coordIdtoCoordName9(sendNum);
		String convertRst10 = coordDao.coordIdtoCoordName10(sendNum);
	
		request.setAttribute("convertRst1",convertRst1);
		request.setAttribute("convertRst2",convertRst2);
		request.setAttribute("convertRst3",convertRst3);
		request.setAttribute("convertRst4",convertRst4);
		request.setAttribute("convertRst5",convertRst5);
		request.setAttribute("convertRst6",convertRst6);
		request.setAttribute("convertRst7",convertRst7);
		request.setAttribute("convertRst8",convertRst8);
		request.setAttribute("convertRst9",convertRst9);
		request.setAttribute("convertRst10",convertRst10);
		
		ArrayList<String> tagNB = new ArrayList<>();
		
		try { 
			Scanner scanner2 = new Scanner(new File("c:\\tagCount.csv")); 
			scanner2.useDelimiter(System.getProperty("line.separator")); 
			while(scanner2.hasNext()) 
				tagNB.add(scanner2.next());
			scanner2.close(); 
		} catch(FileNotFoundException e) { 
			e.printStackTrace(); 
		}
		
		String[] afterReadtag = tagNB.toArray(new String[tagNB.size()]);
		
		String[] temporarySplit1 = new String[2];
		String[] tagNameNum = new String[11];
		String[] tagCountNum = new String[11];
		
		for(int i=1; i<11; i++) {
			temporarySplit1 = afterReadtag[i].split(",");
			tagNameNum[i] = temporarySplit1[0];
			tagCountNum[i] = temporarySplit1[1];
		}
		
		tagNameNum[0] = "0"; // null 방지로 임의의 값 대입
		
		int[] tagNameNumInt = Arrays.asList(tagNameNum).stream().mapToInt(Integer::parseInt).toArray();
		
		Map<String,Integer> sendNum1 = new HashMap<String, Integer>(); 
		
		for(int i=1; i<11; i++) {
			sendNum1.put("tag_id"+i, tagNameNumInt[i]);
		}

		String tconvertRst1 = tagDao.tagIdtoTagName1(sendNum1);
		String tconvertRst2 = tagDao.tagIdtoTagName2(sendNum1);
		String tconvertRst3 = tagDao.tagIdtoTagName3(sendNum1);
		String tconvertRst4 = tagDao.tagIdtoTagName4(sendNum1);
		String tconvertRst5 = tagDao.tagIdtoTagName5(sendNum1);
		String tconvertRst6 = tagDao.tagIdtoTagName6(sendNum1);
		String tconvertRst7 = tagDao.tagIdtoTagName7(sendNum1);
		String tconvertRst8 = tagDao.tagIdtoTagName8(sendNum1);
		String tconvertRst9 = tagDao.tagIdtoTagName9(sendNum1);
		String tconvertRst10 = tagDao.tagIdtoTagName10(sendNum1);
	
		request.setAttribute("tconvertRst1",tconvertRst1);
		request.setAttribute("tconvertRst2",tconvertRst2);
		request.setAttribute("tconvertRst3",tconvertRst3);
		request.setAttribute("tconvertRst4",tconvertRst4);
		request.setAttribute("tconvertRst5",tconvertRst5);
		request.setAttribute("tconvertRst6",tconvertRst6);
		request.setAttribute("tconvertRst7",tconvertRst7);
		request.setAttribute("tconvertRst8",tconvertRst8);
		request.setAttribute("tconvertRst9",tconvertRst9);
		request.setAttribute("tconvertRst10",tconvertRst10);
		
		
		String user_id = (String)request.getSession().getAttribute("user_id");

		if(user_id != null && user_id != "") {
			List<Integer> trips = predictDao.getTrips(user_id);
			PredictDataBean clusters = predictDao.getClu(user_id);
			List<Integer> rstCoords = new ArrayList<Integer>();
			List<Integer> p1coords = new ArrayList<Integer>();
			List<CoordDataBean> coords = new ArrayList<CoordDataBean>();
			try {
				int predict1 = clusters.getPredict1();
				if(predict1!= -1 ) {
					clusters.setPredictName("predict1");
					clusters.setClusterNo(predict1);
					p1coords = predictDao.getCoords(clusters); 
					for(int i=0; i<p1coords.size(); i++) {
						int coord = p1coords.get(i);
						if(!trips.contains(coord)) {
							rstCoords.add(coord);
						}
					}
				}
				
				if(rstCoords.size()<3) {
					int predict2 = clusters.getPredict2();
					clusters.setPredictName("predict2");
					clusters.setClusterNo(predict2);
					List<Integer> p2coords = predictDao.getCoords(clusters);
					for(int i=0; i<p2coords.size(); i++) {
						int coord = p2coords.get(i);
						if(!trips.contains(coord)) {
							if(!rstCoords.contains(coord)) {
								rstCoords.add(coord);
							}
						}
					
					}
				
				}
				
				if(rstCoords.size()<3) {
					int predict3 = clusters.getPredict3();
					clusters.setPredictName("predict3");
					clusters.setClusterNo(predict3);
					List<Integer> p3coords = predictDao.getCoords(clusters);
					for(int i=0; i<p3coords.size(); i++) {
						int coord = p3coords.get(i);
						if(!trips.contains(coord)) {
							if(!rstCoords.contains(coord)) {
								rstCoords.add(coord);
							}
						}
					
					}
				
				}
				
				for(int i=0; i<10; i++) {
					int coord = rstCoords.get(i);
					coords.add(coordDao.getCoordinate(coord));		
				}
				request.setAttribute("coords", coords);
			
				String coord1 = coords.get(0).getCoord_name();
				String coord2 = coords.get(1).getCoord_name();
				String coord3 = coords.get(2).getCoord_name();
				String coord4 = coords.get(3).getCoord_name();
				String coord5 = coords.get(4).getCoord_name();
				String coord6 = coords.get(5).getCoord_name();
				String coord7 = coords.get(6).getCoord_name();
				String coord8 = coords.get(7).getCoord_name();
				String coord9 = coords.get(8).getCoord_name();
				String coord10 = coords.get(9).getCoord_name();
				
				request.setAttribute("coord1", coord1);
				request.setAttribute("coord2", coord2);
				request.setAttribute("coord3", coord3);
				request.setAttribute("coord4", coord4);
				request.setAttribute("coord5", coord5);
				request.setAttribute("coord6", coord6);
				request.setAttribute("coord7", coord7);
				request.setAttribute("coord8", coord8);
				request.setAttribute("coord9", coord9);
				request.setAttribute("coord10", coord10);
				
			} catch(NullPointerException e ) {
				
			}
		}
		

		return new ModelAndView("svc/reportView");
	}
	/////////////////////////////////user pages/////////////////////////////////

	@RequestMapping("/myPage")
	public ModelAndView svcMyPageProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//To find which user is this?
		String user_id=(String)request.getSession().getAttribute("user_id");

		if(user_id!=null) {
			UserDataBean userDto=userDao.getUser(user_id);
			//user_tags is a guest value, we should set it additionally
			userDto.setUser_tags(tagDao.getUserTags(user_id));	//태그 가져오는거 수정.
			request.setAttribute("userDto", userDto);
							
			Map<String, Object> userT = new HashMap<String, Object>();				
			userT.put("user_id", user_id);
			List<TripDataBean> usertrip = tripDao.getReviewList(user_id);
			request.setAttribute("size", usertrip.size());
			List<Integer> tripid = memberDao.getMemTripId(user_id);
			int[] catchNum= new int [tripid.size()] ;
			for(int j=0; j<tripid.size(); j++) {
				int trip = tripid.get(j);
				userT.put("trip_id", trip);				
				catchNum[j] = reviewDao.getReview(userT).size();
				request.setAttribute("catchNum", catchNum);		
				boolean empty= true;
				for(int in : catchNum) {
					if(in != 0) {
						empty =false;
						break;
					}
				}
				if(empty == false) {
					Map<String, Object> user = new HashMap<String, Object>();
					user.put("user_id", user_id);
					
					List<ReviewDataBean> review = reviewDao.stepOne(user);
					List<ReviewDataBean> reviewDto = new ArrayList<ReviewDataBean>();
					for(int i=0; i<review.size(); i++) {
						String users=review.get(i).getUser_id();
						user.put("reviewer_id", users);	
						int trip_id = review.get(i).getTrip_id();
						user.put("trip_id", trip_id);
						ReviewDataBean reviewW = reviewDao.stepTwo(user);
						reviewDto.add(reviewW);					
					}	
					
					request.setAttribute("reviewDto", reviewDto);
					int count = reviewDao.getReviewCount(userT);
					
					Double reviewcount = (double) count;
					request.setAttribute("count", count);
					Double point = (double) reviewDao.getReviewSum(userT);
					Double divide = 0.0;
					try {
						divide =(double) (point/reviewcount);
						divide = Double.parseDouble(String.format("%.2f",divide));
					}catch(ArithmeticException e) {
						divide = 0.0;
					}				
					request.setAttribute("average", divide);
				}else{
					Map<String, Object> userD = new HashMap<String, Object>();
					userD.put("user_id", user_id);
					List<ReviewDataBean> reviewDto = reviewDao.getEvaluation(userD);			
					request.setAttribute("reviewDto", reviewDto);
				
					int reviewCount = reviewDao.countEvaluation(userD);
					Double count = (double) reviewCount;
					request.setAttribute("count", reviewCount);
					Double point = (double) reviewDao.sumEvaluation(user_id);
					Double divide = 0.0;
					try {
						divide =(double) (point/count);						
						divide = Double.parseDouble(String.format("%.2f",divide));
					}catch(ArithmeticException e) {
						divide = 0.0;
					}					
					request.setAttribute("average", divide);
					
				}
			}
		}
		return new ModelAndView("svc/myPage");
	}

	@RequestMapping("/myTrip")
	public ModelAndView SvcMyTripProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//To find which user is this?
		String user_id=(String)request.getSession().getAttribute("user_id");
		//get this user's trip list
		List<TripDataBean> myTrips=tripDao.getUserTripList(user_id);
		//set Trip List for display
		request.setAttribute("myTrips", myTrips);
		return new ModelAndView("svc/myTrip");
	}
	@RequestMapping("/coordReview")
	public ModelAndView SvcCoordReviewProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int coord_id = Integer.parseInt(request.getParameter("coord_id"));
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("")){
			pageNum = "1";
		}		
		int count = 0;
		Map<String, Object>user = new HashMap<String, Object>();
		user.put("coord_id", coord_id);
		count = coordReviewDao.getReviewCount(user);			
		setReviewLogic(request, pageNum, count, start, end);
		user.put("start", start);
		user.put("end", postPerPage);
		List<CoordReviewDataBean> review = coordReviewDao.getCoordReview(user);
		List<CoordReviewDataBean> coord = new ArrayList<CoordReviewDataBean>();
		
		CoordDataBean coordDto = coordDao.getCoordinate(coord_id);		
		request.setAttribute("review", review);
		request.setAttribute("coord", coordDto);
		return new ModelAndView("svc/coordReview");
	}
	@RequestMapping("/memberReview")
	public ModelAndView SvcMemberReviewProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id=(String)request.getSession().getAttribute("user_id");
		
		int trip_id = Integer.parseInt(request.getParameter("trip_id"));
		Map<String, Object>user = new HashMap<String, Object>();
		List<MemberDataBean>member = memberDao.getMembers(trip_id);
		ArrayList<List <ReviewDataBean>> users  = new ArrayList<List <ReviewDataBean>>();
		
		for(int i=0; i<member.size(); i++) {
			String memId = member.get(i).getUser_id();
			user.put("user_id", memId);
			List<ReviewDataBean> bean = new ArrayList<ReviewDataBean>();
			List<ReviewDataBean>recentTo = reviewDao.getRecent(user);
			ReviewDataBean bestTo = reviewDao.getBest(user);
			ReviewDataBean worstTo = reviewDao.getWorst(user);
			bean.add(bestTo);
			bean.addAll(recentTo);
			bean.add(worstTo);
			request.setAttribute("bean", bean);
			users.add(bean);	
			
		}

		request.setAttribute("users", users);
		return new ModelAndView("svc/memberReview");
	}
	private static final int pageSize=10;
	private static final int pageBlock = 5;
	// 게시판 연산 로직
		private int start;
		private int end;
		private Object[] tagInt ;
		public void setReviewLogic(HttpServletRequest request, String pageNum, int count, int start, int end){
			int currentPage = Integer.parseInt(pageNum);
			int pageCount = count / pageSize + (count % pageSize>0 ? 1:0 );
			if( currentPage > pageCount ) currentPage = pageCount;
			start = ( currentPage - 1 )*pageSize + 1;					
			end = start + pageSize - 1;	
			
			if(end > count) end = count;
			
			int number = count - (currentPage - 1) * pageSize;				
				
			int startPage = (currentPage / pageBlock) * pageBlock+1;  		
			if(currentPage % pageBlock == 0) startPage -= pageBlock;
			int endPage = startPage + pageBlock - 1;
			if(endPage > pageCount ) endPage = pageCount;
		
			request.setAttribute( "count", count );
			request.setAttribute( "pageNum", pageNum );
			request.setAttribute( "currentPage", currentPage );
			request.setAttribute( "number", number );
			request.setAttribute( "startPage", startPage );
			request.setAttribute( "endPage", endPage );
			request.setAttribute( "pageCount", pageCount );
			request.setAttribute( "pageBlock", pageBlock );
			
		}
	@RequestMapping("/reviewPage")
	public ModelAndView SvcReviewProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id = (String) request.getSession().getAttribute("user_id");
		Map<String, Object> user = new HashMap<String, Object>();				
		user.put("user_id", user_id);
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("")){
			pageNum = "1";
		}
		
		int count = 0;
		
		List<Integer> tripid = memberDao.getMemTripId(user_id);
		int catchNum[] = new int[tripid.size()];
		for(int j=0; j<tripid.size(); j++) {
			int trip = tripid.get(j);
			user.put("trip_id", trip);
			int catchNumber =reviewDao.getReview(user).size();
			catchNum[j] = catchNumber;	
			if(catchNum[j] !=0) {
				
				List<ReviewDataBean> review = reviewDao.stepOne(user);
				List<ReviewDataBean> reviewDto = new ArrayList<ReviewDataBean>();
				for(int i=0; i<review.size(); i++) {
					String users=review.get(i).getUser_id();
					user.put("reviewer_id", users);	
					int trip_id = review.get(i).getTrip_id();
					user.put("trip_id", trip_id);
					ReviewDataBean reviewW = reviewDao.stepTwo(user);
					reviewDto.add(reviewW);					
				}			
				count = reviewDao.getReviewCount(user);			
				 setReviewLogic(request, pageNum, count, start, end);
				user.put("start", start);
				user.put("end", postPerPage);
				reviewDto = reviewDao.getReviewFin(user);
				request.setAttribute("reviewDto", reviewDto);
				
			}else {						
				count = reviewDao.countEvaluation(user);
				setReviewLogic(request, pageNum, count, start, end);				
				user.put("start", start);
				user.put("end", postPerPage);
				List<ReviewDataBean>reviewDto = reviewDao.getEvaluationFin(user);
				
				request.setAttribute("reviewDto", reviewDto);			
			}
		}
		return new ModelAndView("svc/reviewPage");
	}
	@RequestMapping("/coordPage")
	public ModelAndView SvcCoordReviewPageProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id = (String) request.getSession().getAttribute("user_id");
		Map<String, Object> user = new HashMap<String, Object>();				
		user.put("user_id", user_id);
		int count = 0;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("")){
			pageNum = "1";
		}
		
		List<CoordDataBean> coord = coordDao.coordAll();
		
		for(int i=0; i<coord.size();i++) {
			int coord_id = coord.get(i).getCoord_id();
			user.put("coord_id", coord_id);
			count = coordReviewDao.getReviewCount(user);
			setReviewLogic(request, pageNum, count, start, end);
			user.put("start", start);
			user.put("end", postPerPage);
			//review add
			coord.get(i).setCoordReview(coord_id);
			
			List<CoordReviewDataBean> list = coordReviewDao.getCoordReview(user);
			//AVERAGE-POINT
			Double reviewCount = (double) count;
			Double point = (double) coordReviewDao.getCReviewSum(user);
			Double divide = 0.0;
			try {
				divide =(double) (point/reviewCount);						
				divide = Double.parseDouble(String.format("%.2f",divide));
			}catch(ArithmeticException e) {
				divide = 0.0;
			}		
			coord.get(i).setAverage(divide);
			// country info
			String countryname = countryDao.getCountryName(coord_id);
			coord.get(i).setCountry_name(countryname);
			//tags add
			List<TripDataBean>trip = tripDao.getTripListByCoord(coord_id);
			List<TagDataBean> boardTags = new ArrayList<TagDataBean>();
			for(int k=0; k<trip.size(); k++) {
				int board_no = trip.get(k).getBoard_no();
				BoardDBBean board = new BoardDBBean();
				BoardDataBean boardDto = board.getPost(board_no);
				List<TagDataBean> tags = boardDto.getBoard_tags();
				boardTags.addAll(tags);
			}	
			List<String>tagValue = new ArrayList<String>();
			List<TagDataBean> tagtag = new ArrayList<TagDataBean>();
			int tagInt[] = new int [boardTags.size()];
			for(int j=0; j<boardTags.size(); j++) {
				try {
				tagValue.add(boardTags.get(j).getTag_value());	
				Map<String, Integer> map = new HashMap<>();
					for(String temp : tagValue) {
						 Integer counttag = map.get(temp);
					     map.put(temp, (counttag == null) ? 1 : counttag + 1);
					}
					LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
			        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
			        coord.get(i).setMap(reverseSortedMap);
				}catch(NullPointerException e) {
					e.printStackTrace();
				}
			}
			coord.get(i).setBoardtags(tagtag);
			
			
		}		
		
		request.setAttribute("coord", coord);	
		return new ModelAndView("svc/coordPage");
	}
	
	/////////////////////////////////board pages/////////////////////////////////

	//get board posts
	@RequestMapping("/tripList")
	public ModelAndView svcListProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//set row number of select request
		//admin page needs this
		int rowNumber;
		int startPage=0;
		//if(startPage>0) {
		//	rowNumber=startPage*postPerPage;
	//	} else {
			rowNumber=0;
		//}
		
		List<BoardDataBean> postList=boardDao.getPostList(rowNumber, postPerPage);
		//set count and next row info for 'load more list'
		if(postList.size()>=postPerPage) {
			request.setAttribute("next_row", postPerPage+1);
		} else if(postList.size()>0&&postList.size()<postPerPage) {
			request.setAttribute("next_row", postList.size()+1);
		} else {
			request.setAttribute("next_row", 0);
		}

		request.setAttribute("postList", postList);
		return new ModelAndView("svc/tripList");
	}

	//get one board post by board_no
	@RequestMapping("/trip")
	public ModelAndView svcTripProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//Which post should we get?
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		request.setAttribute("board_no", board_no);
		//who is the current user?
		String user_id=(String)request.getSession().getAttribute("user_id");
		//get all values of requested board post
		BoardDataBean boardDto=boardDao.getPost(board_no);
		//post not found or was deleted exception
		if(boardDto==null) {
			request.setAttribute("postNotFound", true);
		} else {
			//check, current user is the owner of this post?
			if(boardDto.getUser_id()==user_id) {
				//button display control
				request.setAttribute("isOwner", true);
			}
			//get trips of this post
			for(TripDataBean trip:boardDto.getTripLists()) {
				boolean isMember=false;
				for(MemberDataBean member:trip.getTrip_members()) {
					if(member.getUser_id().equals(user_id)) {
						isMember=true;
					}
				}
				request.setAttribute("isMember", isMember);
			}
			request.setAttribute("boardDto", boardDto);
			boardDao.addViewCount(board_no);
		}

		return new ModelAndView("svc/trip");
	}
	
	//basic search by keyword, in title, content, or writer user_name
	@RequestMapping("/searchTrip")
	public ModelAndView svcSearchProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException, UnsupportedEncodingException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//get the type and keyword of searching
		String selectedType=request.getParameter("search_type");
		String keyword=request.getParameter("keyword");
		request.setAttribute("keyword", keyword);
		//set List
		List<BoardDataBean> foundList;
		//find trips for each type
		if(selectedType.equals("schedule")) {
			foundList=boardDao.findPostByKeyword(keyword);
		} else {
			foundList=boardDao.findPostByUser(keyword);
		}
		//searchTrip Log
		try {
			logDao.searchTripLog(selectedType, keyword);
		} catch (ClassCastException | JsonProcessingException | org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//count check
		int count=0;
		if(foundList.size()>0) {
			count=foundList.size();
		}
		request.setAttribute("foundList", foundList);
		request.setAttribute("count", count);
		return new ModelAndView("svc/foundList");
	}
	
	//advance search by site, start date, end date, period, tag
	@RequestMapping("advanceSearch")
	public ModelAndView advanceSearchProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String searchPeriod = request.getParameter("searchPeriod");
		String searchTag = request.getParameter("searchTag");
		String searchSite = request.getParameter("searchSite");

		if(toDate == "" || toDate == null) {
			toDate="01/01/2100";
		}
		
		if(fromDate == "" || fromDate == null) {
			fromDate="01/01/1970";
		}
			
		java.sql.Timestamp timeStampDateTo;
		java.sql.Timestamp timeStampDateFrom;
		try {
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			Date dateTo = formatter.parse(fromDate);
			Date dateFrom = formatter.parse(toDate);
			timeStampDateTo = new Timestamp(dateTo.getTime());
			timeStampDateFrom = new Timestamp(dateFrom.getTime());
			
		}catch (ParseException e) {
			    return null;
		}
		
		Map<String, Object> searchMap = new HashMap<String, Object>();	
		searchMap.put("fromDate",fromDate);
		searchMap.put("toDate", toDate);
		searchMap.put("searchPeriod", searchPeriod);
		searchMap.put("searchTag", searchTag);
		searchMap.put("searchSite", searchSite);
		
		BoardDataBean boardDto = new BoardDataBean();
		boardDto.setFromDate(fromDate);
		boardDto.setToDate(toDate);
		boardDto.setSearchPeriod(searchPeriod);
		boardDto.setSearchTag(searchTag);
		boardDto.setSearchSite(searchSite);
		
		try {
			logDao.makeAdvanceSearchLog(boardDto);
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String queryHead = "select * from pao_view_board where board_no in (select board_no from pao_trip";
		
		String queryDate = "";
		if(toDate != null || fromDate != null) {
			queryDate += " where start_date>=STR_TO_DATE("+"'"+fromDate+"'"+", '%m/%d/%Y') and end_date<=STR_TO_DATE("+"'"+toDate+"'"+",'%m/%d/%Y')";
		}
		
		String queryPeriod = "";
		if(searchPeriod == null || searchPeriod == "") {
			queryPeriod += "";
		}else if(searchPeriod != null) {
			queryPeriod += " and DATEDIFF(end_date,start_date)="+"'"+searchPeriod+"')";
		}
		
		String query = queryHead + queryDate + queryPeriod;
		
		if(searchPeriod == null || searchPeriod == "") {
			query += ")";
		}
		
		searchMap.put("query", query);
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("")){
			pageNum = "1";
		}
		
		
		if(query != null) {
			List<BoardDataBean> searchReceive = boardDao.advanceSearchByDatePeriod(searchMap);
			
			int rowNumber;
			int startPage=0;
			if(startPage>0) {
				rowNumber=startPage*postPerPage;
			} else {
				rowNumber=0;
			}
			
			int startRowNumber = rowNumber;
			int endRowNumber = postPerPage;
			
			searchMap.put("startRowNumber", rowNumber);
			searchMap.put("endRowNumber", rowNumber*postPerPage);
			
			if(searchReceive.size()>=postPerPage) {
					request.setAttribute("next_row", postPerPage+1);
				} else if(searchReceive.size()>0&&searchReceive.size()<postPerPage) {
					request.setAttribute("next_row", searchReceive.size()+1);
				} else {
					request.setAttribute("next_row", 0);
				}
			
			boardDto.setStartRowNumber(rowNumber);
			boardDto.setEndRowNumber(rowNumber*postPerPage);
	
			System.out.println(rowNumber);		
			
		//	String querylimit = " limit "+startRowNumber+", "+endRowNumber+"";
			System.out.println("kki");

		//	query += querylimit;			

			searchMap.put("query", query);
			System.out.println(query);
			searchReceive = boardDao.advanceSearchByDatePeriod(searchMap);
			request.setAttribute("searchReceive", searchReceive);
			
		}
	

		if(searchSite != "" && searchSite != null) {
			List<BoardDataBean> searchReceive = boardDao.advanceSearchBySite(searchMap);
		    System.out.println("검색결과 개수 장소: "+searchReceive.size()+"\n");
		    
			int rowNumber;
			int startPage=0;
			if(startPage>0) {
				rowNumber=startPage*postPerPage;
			} else {
				rowNumber=0;
			}
	
			int startRowNumber = rowNumber;
			int endRowNumber = postPerPage;
			
			searchMap.put("startRowNumber", rowNumber);
			searchMap.put("endRowNumber", rowNumber*postPerPage);
		   
		    if(searchReceive.size()>=postPerPage) {
				request.setAttribute("next_row", postPerPage+1);
			} else if(searchReceive.size()>0&&searchReceive.size()<postPerPage) {
				request.setAttribute("next_row", searchReceive.size()+1);
			} else {
				request.setAttribute("next_row", 0);
			}
		    boardDto.setStartRowNumber(rowNumber);
			boardDto.setEndRowNumber(rowNumber*postPerPage);
	
			System.out.println(rowNumber);		
			System.out.println("kki2");

			searchReceive = boardDao.advanceSearchBySite(searchMap);
			request.setAttribute("searchReceive", searchReceive);
		}
	    
		if(searchTag != "" && searchTag != null) {
			List<BoardDataBean> searchReceive = boardDao.advanceSearchByTag(searchMap);
		    System.out.println("검색결과 개수 태그: "+searchReceive.size()+"\n");
		    
			int rowNumber;
			int startPage=0;
			if(startPage>0) {
				rowNumber=startPage*postPerPage;
			} else {
				rowNumber=0;
			}
			
			int startRowNumber = rowNumber;
			int endRowNumber = postPerPage;
						
			searchMap.put("startRowNumber", rowNumber);
			searchMap.put("endRowNumber", rowNumber*postPerPage);
		  
		    if(searchReceive.size()>=postPerPage) {
				request.setAttribute("next_row", postPerPage+1);
			} else if(searchReceive.size()>0&&searchReceive.size()<postPerPage) {
				request.setAttribute("next_row", searchReceive.size()+1);
			} else {
				request.setAttribute("next_row", 0);
			}
		    boardDto.setStartRowNumber(rowNumber);
			boardDto.setEndRowNumber(rowNumber*postPerPage);
	
			System.out.println(rowNumber);		

			System.out.println(query);
			searchReceive = boardDao.advanceSearchByTag(searchMap);
			request.setAttribute("searchReceive", searchReceive);
			
		}
		return new ModelAndView("svc/advanceSearch");
		
}
	/////////////////////////////////album pages/////////////////////////////////

	@RequestMapping("/album")
	public ModelAndView svcAlbumProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//get all photos and its amount from pao_album
		List<AlbumDataBean> album=albumDao.getAllPhotos();
		int photoCount=album.size();
		request.setAttribute("photoCount", photoCount);
		if(photoCount>0) {
			request.setAttribute("album", album);
		}
		return new ModelAndView("svc/album");
	}

	@RequestMapping("/svc/boardAlbum")//svc/boardAlbum
	public ModelAndView svcBoardAlbumProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int trip_id=Integer.parseInt(request.getParameter("trip_id"));
		String user_id=(String)request.getSession().getAttribute("user_id");
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		String isMemberOfThisTrip=(String)request.getParameter("isMemberOfThisTrip");
		request.setAttribute("board_no", board_no);
		request.setAttribute("trip_id", trip_id);
		//always first page, load next page by ajax
		List<AlbumDataBean> photoList=albumDao.getPhotosByTripId(trip_id);
		if(photoList.size()>0) {
			int photoPages=photoList.size()/photoPerPage;
			request.setAttribute("photoPages", photoPages);
			request.setAttribute("photoList", photoList);
		}
		boolean isMember=false;
		if(isMemberOfThisTrip.equals("true")) isMember=true;
		request.setAttribute("isMember", isMember);
		request.setAttribute("size", photoPerPage);
		return new ModelAndView("svc/boardAlbum");
	}

	/////////////////////////////////ajax method list/////////////////////////////////
	@RequestMapping(value="/loadMoreList", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<BoardDataBean> loadMoreList(int next_row) {
		//get more 10 trip posts when 'load more' button is pressed
		List<BoardDataBean> additionalList=boardDao.getPostList(next_row, postPerPage);
		return additionalList;
	}
	
}
