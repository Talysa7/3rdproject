package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.SqlMapClient;

@Controller
public class TestHandler {
	
	@RequestMapping("/test")
	public ModelAndView svcDefaultProcess(HttpServletRequest request, HttpServletResponse response) throws HandlerException {
		SqlSession session=SqlMapClient.getSession();
		String name = session.selectOne("test");
		request.setAttribute("name", name);
		return new ModelAndView("test");
	}
}
