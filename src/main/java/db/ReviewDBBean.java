package db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class ReviewDBBean {
private SqlSession session=SqlMapClient.getSession();
	public int beforeReview(Map<String, Object> userT){
		return session.selectOne("user.beforeReview", userT);
	}
	public List<ReviewDataBean> stepOne(Map<String, Object> user) {
		return session.selectList("user.stepOne", user);
	}
	public ReviewDataBean stepTwo(Map<String, Object> user) {
		return session.selectOne("user.stepTwo", user);
	}
	public List<ReviewDataBean> getReview(Map<String, Object>user){
		return session.selectList("user.getReview", user);
	}
	public List<ReviewDataBean> getEvaluation(Map<String, Object> user) {
		return session.selectList("user.getEvaluation", user);
	}
	public int insertEvaluation(ReviewDataBean evalDto) {
		return session.insert("user.insertEvaluation", evalDto);
	}
	public int countEvaluation(Map<String, Object> userD) {
		int count=0;
		try {
			count = session.selectOne("user.countEvaluation", userD);
		}catch(NullPointerException e) {
			count = 0;
		}
		return count;
	}
	public int getReviewCount(Map<String, Object> userT) {
		return session.selectOne("user.getReviewCount", userT);
	}
	public int sumEvaluation(String user_id) {
		Map<String, Object> user = new HashMap<String, Object>();
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
	public int getReviewSum(Map<String, Object> userT) {
		ReviewDBBean reviewDao = new ReviewDBBean();
		int count = reviewDao.getReviewCount(userT);
		List<Integer> sum ;		
		int hap=0;
		if(count>0) {
		 sum =  session.selectList("user.getReviewSum", userT);
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
	public List<ReviewDataBean> getReviewFin(Map<String, Object> user, int rowNumber) {
		user.put("startRowNumber", rowNumber);
		if(rowNumber>0) {
			user.put("endRowNumber", rowNumber*5);
		} else {
			user.put("endRowNumber", 5);
		}	
		List<ReviewDataBean> postList=session.selectList("user.getReviewFin", user);
		return postList;		
	}
	public List<ReviewDataBean> getEvaluationFin(Map<String, Object> user, int rowNumber) {
		user.put("startRowNumber", rowNumber);
		if(rowNumber>0) {
			user.put("endRowNumber", rowNumber*5);
		} else {
			user.put("endRowNumber", 5);
		}	
		List<ReviewDataBean> postList=session.selectList("user.getEvalFin", user);
		return postList;
	}
	
	
}
