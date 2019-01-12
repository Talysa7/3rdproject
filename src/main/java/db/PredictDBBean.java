package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class PredictDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	public List<Integer> getTrips(String user_id) {
		return session.selectList("predict.getTrips",user_id);
	}
	
	public PredictDataBean getClu(String user_id) {
		return session.selectOne("predict.getClu",user_id);
	}
	
	public List<Integer> getCoords(PredictDataBean clusters){
		return session.selectList("predict.getCoords", clusters);
	}
	
}
