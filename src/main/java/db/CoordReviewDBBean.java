package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class CoordReviewDBBean {
	private SqlSession session=SqlMapClient.getSession();
	public List<CoordReviewDataBean> getCoordReview(Map<String, Object> user) {
		return session.selectList("location.getCoordReview", user);
	}
	public int insertCoordReview(CoordReviewDataBean coordreDto) {
		return session.insert("location.insertCoordReview", coordreDto);
	}
	public int getCoordReviewCount() {
		return session.selectOne("location.countCoordReview");
	}
	public List<CoordReviewDataBean> getAll(Map<String, Object> user) {
		return session.selectList("location.getAll", user);
	}
	public int getReviewCount(Map<String, Object> user) {
		return session.selectOne("location.getCReviewCount", user);
	}
	public int getCReviewSum(Map<String, Object> userT) {
		ReviewDBBean reviewDao = new ReviewDBBean();
		int count = reviewDao.getReviewCount(userT);
		List<Integer> sum ;		
		int hap=0;
		if(count>0) {
		 sum =  session.selectList("location.getCReviewSum", userT);
		 int[] summit= new int[sum.size()] ;
		 for(int i=0; i<sum.size(); i++) {
			 summit[i]=sum.get(i);
			 hap += summit[i];
		 }
		}

		return hap;
	}
	public List<CoordReviewDataBean> coordReview(int coord_id) {
		return session.selectList("location.coordReview", coord_id);
	}
}
