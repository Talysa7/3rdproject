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
	
	@RequestMapping("/tripList")
	public ModelAndView svcListProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		UserDataBean userDto=(UserDataBean)request.getAttribute("userDto");
		List<BoardDataBean> tripList=boardDao.getTripList();//FIXME : start End 값 줄필요가 있음.
		int startTrip=0;
		int endTrip=0;
		if(tripList.size()>=10) {
			request.setAttribute("last_row", 11);
		} else {
			request.setAttribute("last_row", tripList.size()+1);
		}
		
		int count=boardDao.getCount();
		request.setAttribute("userDto", userDto);
		request.setAttribute("tripList", tripList);
		request.setAttribute("startTrip", startTrip);
		request.setAttribute("endTrip", endTrip);
		request.setAttribute("count", count);
		return new ModelAndView("svc/tripList");
	}
	
	@RequestMapping("/trip")
	public ModelAndView svcTripProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id=(String)request.getSession().getAttribute("user_id");
		request.setAttribute("user_id", user_id);
		
		//get board_no of the post
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		request.setAttribute("board_no", board_no);
		
		//getTrip-게시물 정보 가져오기
		BoardDataBean boardDto=boardDao.getBoard(board_no);
		request.setAttribute("boardDto", boardDto);
		
		//trip details
		List<CoordDataBean> coordDtoList=new ArrayList<CoordDataBean>();
		//tbDto has td_trip_ids
		if(boardDto.getTd_trip_ids().length>0) {		//FIXME : trip 이슈
			for(int trip_id:boardDto.getTd_trip_ids()) {
				CoordDataBean coordDto=coordDao.getTripDetail(trip_id);
				coordDtoList.add(coordDto);
			}
			request.setAttribute("locDtoList", coordDtoList);
		}
		
		boardDao.addCount(board_no);
		
		//authorization for deletion and modification-수정 삭제 권한 
		TripDataBean tripDto=new TripDataBean();
		tripDto.setBoard_no(board_no);
		user_id=(user_id==null?"":user_id);
		tripDto.setUser_id(user_id);		//데이터 빈에 userId X 네임만 있음.
		int isOwner=tripDao.isOwner(tripDto);
		request.setAttribute("isOwner", isOwner);
		
		//determine tab
		String tab=request.getParameter("tab");
		if(tab==null)tab=MAP;
		request.setAttribute("tab", tab);
		
		//map data
		//test
		double lat=37.554690;
		double lng=126.970702;
		//
		request.setAttribute("lat",lat);
		request.setAttribute("lng", lng);
		
		//board album data	
		String start=request.getParameter("start");
		if(start==null)start="1";
		request.setAttribute("start",start);
		
		//member Info of each trip
		List<TripDataBean> memInfoList=memberDao.getMemInfoList(board_no);
		TripDataBean tripDto1 = new TripDataBean();
		boolean isMember=false;
		for(TripDataBean trip1Dto : memInfoList) {
			int trip_id=tripDto1.getTrip_id();
			List<MemberDataBean> currendMember=memberDao.getMember(trip_id);
			String members="";
			if (currendMember.size()>0) {
				for(int i=0; i<currendMember.size(); i++) {
					if (currendMember.size()<=1) {
						members=members+currendMember.get(i).getUser_name();
					} else if(currendMember.size()>1 && i==currendMember.size()-1) {
						members=members+currendMember.get(i).getUser_name();
					} else {
						members=members+currendMember.get(i).getUser_name()+" ";
					}
					String id=(String)request.getSession().getAttribute("user_id");
					if(id!=null) {
						String name=memberDao.getUserName(id);
						if(name.equals(currendMember.get(i).getUser_name())) {
							isMember=true;
						}
					}
				}
			}
			
		}
		
		request.setAttribute("memInfoList", memInfoList);
		request.setAttribute("isMember", isMember);

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
	@RequestMapping(value="/loadMoreList", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<BoardDataBean> loadMoreList(int last_row) {
		//get more 5 trip articles when 'load more' button is pressed
		List<BoardDataBean> additionalList=boardDao.loadMoreList(last_row);//FIXME: 이 메소드 삭제함. list메소드 하나로 쓰기로 하였으니 참고.
		
		return additionalList;
	}
}
