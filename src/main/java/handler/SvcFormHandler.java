package handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import db.AlbumDBBean;
import db.CmtDBBean;
import db.CoordDBBean;
import db.CoordDataBean;
import db.CountryDBBean;
import db.CountryDataBean;
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
public class SvcFormHandler {
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
	private BoardDBBean boardDao;
	@Resource
	private UserDBBean userDao;
	@Resource
	private CountryDBBean countryDao;
	@Resource
	private MemberDBBean memberDao;
	@Resource
	private ReviewDBBean reviewDao;
	
	/////////////////////////////////user pages/////////////////////////////////
	@RequestMapping("/EmailId")
	public ModelAndView svcEmailIdProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/EmailId");
	}
	
	@RequestMapping("/EmailPasswd")
	public ModelAndView svcEmailPasswd(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/EmailPasswd");
	}
	
	@RequestMapping("/Conditions")
	public ModelAndView svcConditionsProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView("svc/Conditions");
	}

	@RequestMapping("/registration")
	public ModelAndView svcRegProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		List<TagDataBean> styleTags=tagDao.getStyleTags();
		request.setAttribute("styleTags", styleTags);
		return new ModelAndView("svc/registration");
	}
	
	@RequestMapping( "/login" )
	public ModelAndView LoginProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/login" );
	}
	
	@RequestMapping( "/userModPassCheck" )
	public ModelAndView userModPassCheckProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/userModPassCheck" );
	}
	
	@RequestMapping( "/userModify" )
	public ModelAndView userModifyProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id=(String)request.getSession().getAttribute("user_id");
		String passwd=request.getParameter("passwd");
		
		int result=0;
		UserDataBean userDto=userDao.getUser(user_id);
		
		if(userDto!=null) {
			if(passwd.equals(userDto.getPasswd())) {
				result=1;
				request.setAttribute("userDto", userDto);
				List<TagDataBean> tagList=tagDao.getStyleTags();
				request.setAttribute("tagList", tagList);
				List<TagDataBean> userTags=tagDao.getUserTags(user_id);
				request.setAttribute("userTags", userTags);
			} else {
				result=0;
			}
		} else {
			result=-1;
		}
		
		request.setAttribute("result", result);
		
		return new ModelAndView( "svc/userModify" );
	}
	
	@RequestMapping( "/userLeave" )
	public ModelAndView DeleteProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		return new ModelAndView( "svc/userLeave" );
	}
	
	/////////////////////////////////board pages/////////////////////////////////
	
	@RequestMapping("/tripWrite")
	public ModelAndView svcTripWriteFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//Need to know the writer: Bring user_id from session & user_name(nickname)
		String user_id=(String)request.getSession().getAttribute("user_id");
		String user_name= userDao.getUserName(user_id);//put user_id from userDB and get user_name
		
		List<TagDataBean> styleTags=tagDao.getStyleTags();
		
		//send them to set User Name on the form
		request.setAttribute("user_id", user_id); 
		request.setAttribute("user_name", user_name); 
		request.setAttribute("styleTags", styleTags); 

		return new ModelAndView("svc/tripWrite"); 
	}
	
	@RequestMapping("/tripMod")
	public ModelAndView svcTripModFormProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//get the origin;
		//basic contents(essential 'var' for tripMod.jsp: board_no, user_id, board_content, board_m_num, board_talk, td_trip_id, locs[], tags[])
		//get user_name from pao_user
		String user_id = (String) request.getSession().getAttribute("user_id");
		request.setAttribute("user_name", userDao.getUserName(user_id));
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		BoardDataBean boardDto=boardDao.getBoard(board_no);
		request.setAttribute("boardDto", boardDto);
		
		List<TripDataBean> tripList = tripDao.getBoardTripList(board_no);
		request.setAttribute("tripList", tripList);
		
		//get tag details & total list
		//we don't need getStyleTag, we will use tag_type
		List<TagDataBean> tripTags=tagDao.getPostTags(board_no);
		//List<TagDataBean> tagList=tagDao.getStyleTags();
		//request.setAttribute("tagList", tagList);	
		request.setAttribute("tripTags", tripTags); 
		
		return new ModelAndView("svc/tripMod");
	}
	
	@RequestMapping("/review")
	public ModelAndView svcReviewProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		Map<String, Object> user = new HashMap<String, Object>();
		String user_id = (String) request.getSession().getAttribute("user_id");
		request.setAttribute("user", user_id);
		List<TripDataBean> usertrip = tripDao.getUserTripList(user_id);
		List<TripDataBean> trip = new ArrayList<TripDataBean>();
		for(int i=0; i<usertrip.size(); i++) {		
			TripDataBean tripDto = usertrip.get(i);
			tripDto.setCoordinate(tripDto.getTrip_id());
			tripDto.setReview_members(tripDto.getTrip_id());			
			trip.add(tripDto);			
		}		
		request.setAttribute("trip", trip);
		return new ModelAndView("svc/review");		
	}
	@RequestMapping("/placeWrite")
	public ModelAndView svcPlaceWriteProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id = (String) request.getSession().getAttribute("user_id");
		request.setAttribute("user_id", user_id);
		List<Integer> trip_id = memberDao.getMemTripId(user_id);
		request.setAttribute("trip_id", trip_id);
		List<TripDataBean> usertrip = tripDao.getReviewList(user_id);
		List<TripDataBean> trip = new ArrayList<TripDataBean>();
		for(int i=0; i<usertrip.size(); i++) {		
			TripDataBean tripDto = usertrip.get(i);
			tripDto.setCoordinate(tripDto.getTrip_id());
			trip.add(tripDto);			
		}	
		request.setAttribute("trip", trip);
		return new ModelAndView("svc/placeWrite");		
	}
}
