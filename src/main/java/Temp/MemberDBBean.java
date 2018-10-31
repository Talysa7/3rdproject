package Temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import db.BoardDataBean;
import Temp.UserDataBean;

public class MemberDBBean {
	private SqlSession session=SqlMapClient.getSession();

	public List<UserDataBean> getCurrentMember(int trip_id) {
		List<UserDataBean> memberList=session.selectList("user.getCurrentMember", trip_id);
		for(UserDataBean user:memberList) {
			user.setUser_name((String)session.selectOne("user.getUserName", user.getUser_id()));
		}
		return memberList;
	}
	
	public String getUserName(String user_id) {
		return session.selectOne("user.getUserName", user_id);
	}
	
	public int delTripMember(MemberDataBean memberDto) {
		return session.delete("user.delTripMember", memberDto);
	}
	
	public boolean isTBMember(BoardDataBean tbDto) {
		int count=session.selectOne("board.isTBMember",tbDto);
		if(count>0)return true;
		else return false;
	}
	
	public List<Map<String, String>> getMemInfoList(int board_no) {
		List<Map<String, String>> memNumList=new ArrayList<Map<String, String>>();
		List<Integer> tripIds=session.selectList("location.getTripIds", board_no);
		
		if(tripIds.size()>0) {
			for(int trip_id:tripIds) {
				Map<String, String> currentTrip=new HashMap<String, String>();
				int memNum=session.selectOne("user.getMemberCount", trip_id);
				String trip_id_string=""+trip_id;
				String memNum_string=""+memNum;
				
				currentTrip.put("trip_id", trip_id_string);
				currentTrip.put("trip_member_count", memNum_string);
				memNumList.add(currentTrip);
			}		
		}
		return memNumList;
	}
	
	public List<UserDataBean> getMember(int board_no) {
		return session.selectList("board.getMember", board_no);
	}

	public int isMember(MemberDataBean memberDto) {
		return session.selectOne("user.isMember", memberDto);
	}

	public int addTripMember(MemberDataBean memberDto) {
		return session.selectOne("user.addTripMember", memberDto);
	}
}
