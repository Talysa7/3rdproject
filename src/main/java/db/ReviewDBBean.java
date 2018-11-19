package db;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class ReviewDBBean {
private SqlSession session=SqlMapClient.getSession();
	
	public ReviewDataBean getEvaluation(String user_id) {
		return session.selectOne("user.getEvaluation", user_id);
	}
	public int insertEvaluation(ReviewDataBean evalDto) {
		return session.insert("user.insertEvaluation", evalDto);
	}
}
