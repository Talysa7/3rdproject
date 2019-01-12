package bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import db.AlbumDBBean;
import db.CmtDBBean;
import db.CoordDBBean;
import db.CoordReviewDBBean;
import db.TagDBBean;
import db.BoardDBBean;
import db.TripDBBean;
import db.UserDBBean;
import db.CountryDBBean;
import db.LogDBBean;
import db.MemberDBBean;
import db.PredictDBBean;
import db.RegionDBBean;
import db.ReviewDBBean;

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
	public CoordDBBean coordDao() {
		return new CoordDBBean();
	}
	@Bean
	public TagDBBean tagDao() {
		return new TagDBBean();
	}
	@Bean
	public BoardDBBean boardDao() {
		return new BoardDBBean();
	}
	@Bean
	public TripDBBean tripDao() {
		return new TripDBBean();
	}
	@Bean
	public CountryDBBean countryDao() {
		return new CountryDBBean();
	}
	@Bean
	public MemberDBBean memberDao() {
		return new MemberDBBean();
	}
	@Bean
	public RegionDBBean regionDao() {
		return new RegionDBBean();
	}
	@Bean
	public UserDBBean userDao() {
		return new UserDBBean();
	}
	@Bean
	public ReviewDBBean reviewDao() {
		return new ReviewDBBean();
	}
	@Bean
	public CoordReviewDBBean coordReviewDao() {
		return new CoordReviewDBBean();
	}
	@Bean
	public LogDBBean logDao() {
		return new LogDBBean();
	}
	@Bean
	public PredictDBBean predictDao() {
		return new PredictDBBean();
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