package bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import db.AlbumDBBean;
import db.CmtDBBean;
import db.LocDBBean;
import db.TagDBBean;
import db.TbDBBean;
import db.TripDBBean;
import db.UserDBBean;

@Configuration
public class CreateBean {
	@Bean
	public AlbumDBBean albumDao() {
		return new AlbumDBBean();
	}
	@Bean
	public CmtDBBean cmtDao() {
		return new CmtDBBean();
	}
	@Bean
	public LocDBBean locDao() {
		return new LocDBBean();
	}
	@Bean
	public TagDBBean tagDao() {
		return new TagDBBean();
	}
	@Bean
	public TbDBBean tbDao() {
		return new TbDBBean();
	}
	@Bean
	public TripDBBean tripDao() {
		return new TripDBBean();
	}
	@Bean
	public UserDBBean userDao() {
		return new UserDBBean();
	}
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver=new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}