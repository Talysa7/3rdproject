package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class ReviewDBBean {
private SqlSession session=SqlMapClient.getSession();
	
	public List<ReviewDataBean> getEvaluation(Map<String, String> user) {
		return session.selectList("user.getEvaluation", user);
	}
	public int insertEvaluation(ReviewDataBean evalDto) {
		return session.insert("user.insertEvaluation", evalDto);
	}
	public int countEvaluation(Map<String, String> user) {
		return session.selectOne("user.countEvaluation", user);
	}
	public int sumEvaluation(String user_id) {
		return session.selectOne("user.sumEvaluation", user_id);
	}
}
