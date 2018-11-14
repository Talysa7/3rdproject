package db;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class EvaluationDBBean {
private SqlSession session=SqlMapClient.getSession();
	
	public EvaluationDataBean getEvaluation(String user_id) {
		return session.selectOne("user.getEvaluation", user_id);
	}
	public int insertEvaluation(EvaluationDataBean evalDto) {
		return session.insert("user.insertEvaluation", evalDto);
	}
}
