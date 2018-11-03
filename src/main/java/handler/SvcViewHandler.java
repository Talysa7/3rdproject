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
import db.CoordDBBean;
import db.CoordDataBean;
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

	//amount of displayed photos in a page
	private static final int PHOTOSIZE=6;
	//
	private static final String MAP="0";
	//How many articles do we need in a page, default is 10
	private static final int articlePerPage=10;

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
			userDto.setUser_tags(userDto.getUser_id());
			request.setAttribute("userDto", userDto);
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

	/////////////////////////////////board pages/////////////////////////////////

	//get board articles
	@RequestMapping("/tripList")
	public ModelAndView svcListProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//set row number of select request
		//admin page needs this
		int rowNumber;
		int startPage=Integer.parseInt(request.getParameter("pageNum"));
		if(startPage>0) {
			rowNumber=startPage*articlePerPage;
		} else {
			rowNumber=1;
		}
		List<BoardDataBean> tripList=boardDao.getTripList(rowNumber, articlePerPage);
		//set count and next row info for 'load more list'
		if(tripList.size()>=10) {
			request.setAttribute("next_row", articlePerPage+1);
		} else if(tripList.size()>0&&tripList.size()<10) {
			request.setAttribute("next_row", tripList.size()+1);
		} else {
			request.setAttribute("next_row", 0);
		}
		request.setAttribute("tripList", tripList);
		return new ModelAndView("svc/tripList");
	}

	//get one board article by board_no
	@RequestMapping("/trip")
	public ModelAndView svcTripProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		//Which article should we get?
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		//who is the current user?
		String user_id=(String)request.getSession().getAttribute("user_id");

		//get all values of requested board article
		BoardDataBean boardDto=boardDao.getBoard(board_no);
		//article not found or was deleted exception
		if(boardDto==null) {
			request.setAttribute("articleNotFound", true);
		} else {
			//check, current user is the owner of this article?
			if(boardDto.getUser_id()==user_id) {
				//button display control
				request.setAttribute("isOwner", true);
			}
			//get trips of this article
			for(TripDataBean trip:boardDto.getTripLists()) {
				trip.setTrip_members(board_no);
				//check, current user is a member of this article's trips?
				boolean isMember=false;
				for(MemberDataBean member:trip.getTrip_members()) {
					if(member.getUser_id().equals(user_id)) {
						isMember=true;
					}
				}
				request.setAttribute("isMember", isMember);
			}
			request.setAttribute("boardDto", boardDto);
			boardDao.addCount(board_no);
		}

		return new ModelAndView("svc/trip");
	}

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
			foundList=boardDao.findTripByKeyword(keyword);
		} else {
			foundList=boardDao.findTripByUser(keyword);
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
		int count=albumDao.getCount();
		request.setAttribute("count", count);
		if(count>0) {
			//select album
			List<AlbumDataBean>album=albumDao.getAlbum();
			request.setAttribute("album", album);

			//send photo countries and tags
			List<Map<String, String>> photoInfos=new ArrayList<Map<String, String>>();
			List<Map<String, String>> photoTags=new ArrayList<Map<String, String>>();
			if(count>0) {
				int board_no=0;
				for(int i=0; i<album.size(); i++) {
					if(board_no!=album.get(i).getBoard_no()) {
						//board_no of this photo, if it has same with previous one, then pass
						board_no=album.get(i).getBoard_no();
						String this_board_no=""+board_no;

						//send photo countries
						Map<String, String> photoInfo=new HashMap<String, String>();
						photoInfo.put("this_board_no", this_board_no);
						photoInfo.put("photoLoc", coordDao.getPhotoLoc(album.get(i).getBoard_no()));
						photoInfos.add(photoInfo);

						//send photo tags
						List<TagDataBean> photoTag=tagDao.getTripTags(board_no);
						for(TagDataBean tb:photoTag) {
							Map<String, String> tempTags=new HashMap<String, String>();
							tempTags.put("this_board_no", this_board_no);
							tempTags.put("tag_value", tb.getTag_value());
							photoTags.add(tempTags);
						}
					}

				}
			}
			request.setAttribute("photoInfos", photoInfos);
			request.setAttribute("photoTags", photoTags);
		}
		return new ModelAndView("svc/album");
	}

	@RequestMapping("/svc/boardAlbum")//svc/boardAlbum
	public ModelAndView svcBoardAlbumProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		request.setAttribute("board_no", board_no);

		String user_id=(String) request.getSession().getAttribute( "user_id" );
		if(user_id==null)user_id="";

		int count=albumDao.getBoardCount(board_no);
		request.setAttribute("count", count);

		if(count>0) {
			//page
			int start=Integer.parseInt(request.getParameter("start"));
			int end=start+PHOTOSIZE-1;
			int last=(count%PHOTOSIZE==0?count-PHOTOSIZE:(count/PHOTOSIZE)*PHOTOSIZE);//+(count%PHOTOSIZE==0?0:1));

			request.setAttribute("start",start);
			request.setAttribute("size", PHOTOSIZE);
			request.setAttribute("last",last);

			//select board album
			Map<String, Integer>map=new HashMap<String,Integer>();
			map.put("start",start);
			map.put("end", end);
			map.put("board_no", board_no);
			List<AlbumDataBean>album=albumDao.getBoardAlbum(map);
			request.setAttribute("album", album);
		}

		//check user whether user is member or not
		BoardDataBean tbDto=new BoardDataBean();
		user_id=(user_id==null?"":user_id);
		tbDto.setUser_id(user_id);
		tbDto.setBoard_no(board_no);
		boolean isMember=memberDao.isTBMember(tbDto);
		request.setAttribute("isMember", isMember);
		return new ModelAndView("svc/boardAlbum");
	}

	/////////////////////////////////ajax method list/////////////////////////////////
	@RequestMapping(value="/loadMoreList", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<BoardDataBean> loadMoreList(int next_row) {
		//get more 10 trip articles when 'load more' button is pressed
		List<BoardDataBean> additionalList=boardDao.getTripList(next_row, articlePerPage);
		return additionalList;
	}
}
