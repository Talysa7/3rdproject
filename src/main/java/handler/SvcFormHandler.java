package handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import db.AlbumDBBean;
import db.CmtDBBean;
import db.LocDBBean;
import db.LocDataBean;
import db.TagDBBean;
import db.TagDataBean;
import db.TbDBBean;
import db.TbDataBean;
import db.TripDBBean;
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
	private LocDBBean locDao;
	@Resource
	private TagDBBean tagDao;
	@Resource
	private TbDBBean tbDao;
	@Resource
	private UserDBBean userDao;
	
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
		String user_name= userDao.getUserName(user_id);//user_id �޾Ƽ� db�� �ִ� name ���� �ҷ�����
		
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
		//basic contents(essential 'var' for tripMod.jsp: tb_no, user_id, tb_content, tb_m_num, tb_talk, td_trip_id, locs[], tags[])
		int tb_no=Integer.parseInt(request.getParameter("tb_no"));
		request.setAttribute("tb_no", tb_no);	
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
		//get tag details & total list 
		List<TagDataBean> tripTags=tagDao.getTripTags(tb_no);
		List<TagDataBean> tagList=tagDao.getStyleTags();
		request.setAttribute("tagList", tagList);	
		request.setAttribute("tripTags", tripTags); 
		
		return new ModelAndView("svc/tripMod");
	}
}
