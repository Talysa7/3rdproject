package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class MemberDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	public List<UserDataBean> getCurrentMember(String td_trip_id) {
		List<UserDataBean> memberList=session.selectList("user.getCurrentMember", td_trip_id);
		for(UserDataBean user:memberList) {
			user.setUser_name((String)session.selectOne("db.getUserName", user.getUser_id()));
		}
		return memberList;
	}
	
	public int addTripMember(Map<String, String> addMemberMap) {
		return session.update("db.addTripMember", addMemberMap);
	}
	
	public int delTripMember(Map<String, String> delMemberMap) {
		return session.update("db.delTripMember", delMemberMap);
	}
	
	public int isMember(Map<String, String> delMemberMap) {
		return session.selectOne("db.isMember", delMemberMap);
	}
	public boolean isTBMember(BoardDataBean tbDto) {
		int count=session.selectOne("db.isTBMember",tbDto);
		if(count>0)return true;
		else return false;
	}
}
