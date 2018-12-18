package handler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
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
import db.CmtDataBean;
import db.CoordDBBean;
import db.CoordDataBean;
import db.CoordReviewDBBean;
import db.CoordReviewDataBean;
import db.CountryDBBean;
import db.CountryDataBean;
import db.LogDBBean;
import db.MemberDBBean;
import db.MemberDataBean;
import db.RegionDataBean;
import db.ReviewDBBean;
import db.ReviewDataBean;
import db.TagDBBean;
import db.TagDataBean;
import db.BoardDBBean;
import db.BoardDataBean;
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
			
			List<Integer> tripid = memberDao.getMemTripId(user_id);
			for(int j=0; j<tripid.size(); j++) {
				int trip = tripid.get(j);
				userT.put("trip_id", trip);
				int catchNumber =reviewDao.getReview(userT).size();
				request.setAttribute("catchNum", catchNumber);			
				if(catchNumber !=0) {
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
		request.setAttribute("user", user_id);
		int trip_id = Integer.parseInt(request.getParameter("trip_id"));
		Map<String, Object>user = new HashMap<String, Object>();
		List<MemberDataBean>member = memberDao.getMembers(trip_id);
		List<ReviewDataBean> recent = new ArrayList<ReviewDataBean>();
		List<ReviewDataBean> worst = new ArrayList<ReviewDataBean>();
		List<ReviewDataBean> best = new ArrayList<ReviewDataBean>();
		ArrayList<List <ReviewDataBean>> users  = new ArrayList<List <ReviewDataBean>>();
		
		for(int i=0; i<member.size(); i++) {
			String memId = member.get(i).getUser_id();
			user.put("user_id", memId);
			List<ReviewDataBean> bean = new ArrayList<ReviewDataBean>();
			List<ReviewDataBean>recentTo = reviewDao.getRecent(user);
			ReviewDataBean bestTo = reviewDao.getBest(user);
			bean.add(bestTo);
			bean.addAll(recentTo);
			bean.add(reviewDao.getWorst(user));
			users.add(bean);
			
		}

		request.setAttribute("users", users);
		request.setAttribute("best", best);
		request.setAttribute("wst", worst);
		request.setAttribute("recent", recent);
		
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
		//get the type and keyword of searching
		String keyword=request.getParameter("keyword");
		request.setAttribute("keyword", keyword);
			
		//find trips for each type
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
					     coord.get(i).setMap(map);
					}
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
		if(startPage>0) {
			rowNumber=startPage*postPerPage;
		} else {
			rowNumber=0;
		}
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
		} catch (ClassCastException | JsonProcessingException | ParseException e) {
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
