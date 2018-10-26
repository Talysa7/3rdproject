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
import db.CmtDBBean;
import db.LocDBBean;
import db.LocDataBean;
import db.TagDBBean;
import db.TagDataBean;
import db.TbDBBean;
import db.TbDataBean;
import db.TripDBBean;
import db.TripDataBean;
import db.UserDBBean;
import db.UserDataBean;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
public class SvcViewHandler {
	private static final int PHOTOSIZE=6;//占쎈립 占쎌넅筌롫똻肉� �빊�뮆�젾占쎈┷占쎈뮉 占쎄텢筌욑옙 揶쏆뮇�땾
	
	private static final String MAP="0";
	
	@Resource
	private TripDBBean tripDao;
	@Resource
	private AlbumDBBean albumDao;
	@Resource
	private CmtDBBean cmtDao;
	@Resource
	private LocDBBean locDao;
	@Resource
	private TagDBBean tagDao;
	@Resource
	private UserDBBean userDao;
	@Resource
	private TbDBBean tbDao;
	
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
		//I don't know why but it fails to get userDto, so here I try to get it.
		String user_id=(String)request.getSession().getAttribute("user_id");
		
		if(user_id!=null) {
			UserDataBean userDto=userDao.getUser(user_id);
			request.setAttribute("userDto", userDto);
			
			List<TagDataBean> userTags=tagDao.getUserTags(userDto.getUser_id());
			request.setAttribute("userTags", userTags);
		}
		return new ModelAndView("svc/myPage");
	}
	
	@RequestMapping("/myTrip")
	public ModelAndView SvcMyTripProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		String user_id=(String)request.getSession().getAttribute("user_id");
		//get user's trip list
		List<LocDataBean> myTrips=locDao.getMyTrips(user_id);
		//put tb_no... it was too much value...
		if(myTrips.size()>0) {
			for(LocDataBean trip:myTrips) {
				int tb_no=tbDao.getTbNo(trip.getTd_trip_id());
				trip.setTb_no(tb_no);
			}
		}
		request.setAttribute("myTrips", myTrips);
		
		return new ModelAndView("svc/myTrip");
	}
	
	/////////////////////////////////board pages/////////////////////////////////
	
	@SuppressWarnings("null")
	@RequestMapping("/tripList")
	public ModelAndView svcListProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		UserDataBean userDto=(UserDataBean)request.getAttribute("userDto");
		List<TbDataBean> tripList=tbDao.getTripList();
		int startTrip=0;
		int endTrip=0;
		if(tripList.size()>=10) {
			request.setAttribute("last_row", 11);
		} else {
			request.setAttribute("last_row", tripList.size()+1);
		}
		
		int count=tbDao.getCount();
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
		
		//get tb_no of the post
		int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		request.setAttribute("tb_no", tb_no);
		
		//getTrip-게시물 정보 가져오기
		TbDataBean tbDto=tbDao.getTb(tb_no);
		request.setAttribute("tbDto", tbDto);
		
		//trip details
		List<LocDataBean> locDtoList=new ArrayList<LocDataBean>();
		//tbDto has td_trip_ids
		if(tbDto.getTd_trip_ids().length>0) {
			for(int trip_id:tbDto.getTd_trip_ids()) {
				LocDataBean locDto=locDao.getTripDetail(trip_id);
				locDtoList.add(locDto);
			}
			request.setAttribute("locDtoList", locDtoList);
		}
		
		tbDao.addCount(tb_no);
		
		//authorization for deletion and modification-수정 삭제 권한 
		TripDataBean tripDto=new TripDataBean();
		tripDto.setTb_no(tb_no);
		user_id=(user_id==null?"":user_id);
		tripDto.setUser_id(user_id);
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
		List<Map<String, String>> memInfoList=tbDao.getMemInfoList(tb_no);
		boolean isMember=false;
		for(Map<String, String> tempMap:memInfoList) {
			String temp_trip_id=""+tempMap.get("td_trip_id");
			List<UserDataBean> currendMember=userDao.getCurrentMember(temp_trip_id);
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
						String name=userDao.getUserName(id);
						if(name.equals(currendMember.get(i).getUser_name())) {
							isMember=true;
						}
					}
				}
			}
			tempMap.put("members", members);
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
		List<TbDataBean> foundList;
		
		//find trips for each type
		if(selectedType.equals("schedule")) {
			foundList=tbDao.findTripByKeyword(keyword);
		} else {
			foundList=tbDao.findTripByUser(keyword);
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
				int tb_no=0;
				for(int i=0; i<album.size(); i++) {
					if(tb_no!=album.get(i).getTb_no()) {
						//tb_no of this photo, if it has same with previous one, then pass
						tb_no=album.get(i).getTb_no();
						String this_tb_no=""+tb_no;
						
						//send photo countries
						Map<String, String> photoInfo=new HashMap<String, String>();
						photoInfo.put("this_tb_no", this_tb_no);
						photoInfo.put("photoLoc", locDao.getPhotoLoc(album.get(i).getTb_no()));
						photoInfos.add(photoInfo);
						
						//send photo tags
						List<TagDataBean> photoTag=tagDao.getTripTags(tb_no);
						for(TagDataBean tb:photoTag) {
							Map<String, String> tempTags=new HashMap<String, String>();
							tempTags.put("this_tb_no", this_tb_no);
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
		int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		request.setAttribute("tb_no", tb_no);
		
		String user_id=(String) request.getSession().getAttribute( "user_id" );
		if(user_id==null)user_id="";
		
		int count=albumDao.getBoardCount(tb_no);
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
			map.put("tb_no", tb_no);
			List<AlbumDataBean>album=albumDao.getBoardAlbum(map);
			request.setAttribute("album", album);
		}
		
		//check user whether user is member or not
		TbDataBean tbDto=new TbDataBean();
		user_id=(user_id==null?"":user_id);
		tbDto.setUser_id(user_id);
		tbDto.setTb_no(tb_no);
		boolean isMember=tbDao.isMember(tbDto);
		request.setAttribute("isMember", isMember);
		return new ModelAndView("svc/boardAlbum");
	}
	
	/////////////////////////////////ajax method list/////////////////////////////////
	@RequestMapping(value="/loadMoreList", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<TbDataBean> loadMoreList(int last_row) {
		//get more 5 trip articles when 'load more' button is pressed
		List<TbDataBean> additionalList=tbDao.loadMoreList(last_row);
		
		return additionalList;
	}
}
