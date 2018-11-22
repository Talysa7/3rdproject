package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class CoordReviewDBBean {
	private SqlSession session=SqlMapClient.getSession();
	public List<CoordReviewDataBean> getCoordReview(int coord_id) {
		return session.selectList("location.getCoordReview", coord_id);
	}
	public int insertCoordReview(CoordReviewDataBean coordreDto) {
		return session.insert("location.getCoordReview", coordreDto);
	}
}
