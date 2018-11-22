package db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class ReviewDBBean {
private SqlSession session=SqlMapClient.getSession();
	public int beforeReview(Map<String, String> userT){
		return session.selectOne("user.beforeReview", userT);
	}
	public List<ReviewDataBean> stepOne(Map<String, Object> user) {
		return session.selectList("user.stepOne", user);
	}
	public ReviewDataBean stepTwo(Map<String, Object> user) {
		return session.selectOne("user.stepTwo", user);
	}
	public List<ReviewDataBean> getEvaluation(Map<String, Object> user) {
		return session.selectList("user.getEvaluation", user);
	}
	public List<ReviewDataBean> getReview(Map<String, String> user) {
		return session.selectList("user.getEvaluation", user);
	}
	public int insertEvaluation(ReviewDataBean evalDto) {
		return session.insert("user.insertEvaluation", evalDto);
	}
	public int countEvaluation(Map<String, String> user) {
		int count=0;
		try {
			count = session.selectOne("user.countEvaluation", user);
		}catch(NullPointerException e) {
			count = 0;
		}
		return count;
	}
	public int getReviewCount(Map<String, String> user) {
		return session.selectOne("user.getReviewCount", user);
	}
	public int sumEvaluation(String user_id) {
		Map<String, String> user = new HashMap<String, String>();
		user.put("user_id", user_id);
		ReviewDBBean reviewDao = new ReviewDBBean();
		int count = reviewDao.countEvaluation(user);
		int hap=0;
		List<Integer> sum;
		if(count>0) {
		 sum =  session.selectList("user.sumEvaluation", user_id);
		 int[] summit = new int[sum.size()];
		 for(int i=0; i<sum.size(); i++) {
			 summit[i] = sum.get(i);
			 hap += summit[i];
		 }
		}
		
		return hap;
	}
	public int getReviewSum(Map<String, String> user) {
		ReviewDBBean reviewDao = new ReviewDBBean();
		int count = reviewDao.getReviewCount(user);
		List<Integer> sum ;		
		int hap=0;
		if(count>0) {
		 sum =  session.selectList("user.getReviewSum", user);
		 int[] summit= new int[sum.size()] ;
		 for(int i=0; i<sum.size(); i++) {
			 summit[i]=sum.get(i);
			 hap += summit[i];
		 }
		}

		return hap;
	}
	public List<MemberDataBean> getReviewMembers(int trip_id) {
		return session.selectList("user.getReviewMembers", trip_id);
	}
}
