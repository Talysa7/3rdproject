package handler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import db.AlbumDBBean;
import db.AlbumDataBean;
import db.BoardDataBean;
import db.CmtDBBean;
import db.CmtDataBean;
import db.CoordDBBean;
import db.CoordDataBean;
import db.CoordReviewDBBean;
import db.CoordReviewDataBean;
import db.MemberDBBean;
import db.MemberDataBean;
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
	private CoordReviewDBBean coordreDao;

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
			int num = reviewDao.beforeReview(userT);
			int number = reviewDao.countEvaluation(userT);
			
			List<Integer> tripid = memberDao.getMemTripId(user_id);
			
			for(int j=0; j<tripid.size(); j++) {
				int trip = tripid.get(j);
				userT.put("trip_id", trip);
				int catchNumber =reviewDao.getReview(userT).size();
				request.setAttribute("catchNum", catchNumber);
			}
			
			int rowNumber=0;			
			int startPage=0;
			if(startPage>0) {
				rowNumber=startPage*3;
			} else {
				rowNumber=0;
			}
			
			if(num!= number) {
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
				if(count>=3) {
					request.setAttribute("next_row", 3+1);
				} else if(count>0&&count<3) {
					request.setAttribute("next_row", count+1);
				} else {
					request.setAttribute("next_row", 0);
				}
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
				if(number>=3) {
					request.setAttribute("next_row", 3+1);
				} else if(number>0&&number<3) {
					request.setAttribute("next_row", number+1);
				} else {
					request.setAttribute("next_row", 0);
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
	public ModelAndView SvcTripReviewProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int coord_id = Integer.parseInt(request.getParameter("coord_id"));
		List<CoordReviewDataBean> review = coordreDao.getCoordReview(coord_id);
		request.setAttribute("review", review);
		return new ModelAndView("svc/coordReview");
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
		if(memberDao.isTripMember(memberDao.getOneMember((user_id)))) isMember=true;
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
	/////////////////////////////ajax method list// review(person)////////////
	@RequestMapping(value = "/loadUserReviewList.go", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ReviewDataBean> reviewSelectProcess(HttpServletRequest request, HttpServletResponse response, int next_row)
			throws HandlerException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String user_id=(String)request.getSession().getAttribute("user_id");
		Map<String, Object> user = new HashMap<String, Object>();				
		user.put("user_id", user_id);
		int num = reviewDao.beforeReview(user);
		int number = reviewDao.countEvaluation(user);
		
		if(num!= number) {			
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
			return reviewDto;
		}else {			
			List<ReviewDataBean> reviewDto = reviewDao.getEvaluation(user);		
			
			request.setAttribute("reviewDto", reviewDto);
			return reviewDto;
		}
	
	}
}
