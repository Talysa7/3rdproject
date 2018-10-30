package Temp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import Temp.UserDataBean;

public class MemberDBBean {
	private SqlSession session=SqlMapClient.getSession();

	public List<UserDataBean> getCurrentMember(String pao_trip_id) {
		List<UserDataBean> memberList=session.selectList("user.getCurrentMember", pao_trip_id);
		for(UserDataBean user:memberList) {
			user.setUser_name((String)session.selectOne("user.getUserName", user.getUser_id()));
		}
		return memberList;
	}
	public int addTripMember(Map<String, String> addMemberMap) {
		return session.update("user.addTripMember", addMemberMap);
	}	
	public int delTripMember(Map<String, String> delMemberMap) {
		return session.update("user.delTripMember", delMemberMap);
	}	
	public int isMember(Map<String, String> delMemberMap) {
		return session.selectOne("user.isMember2", delMemberMap);
	}
}
