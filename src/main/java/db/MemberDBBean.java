package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import db.BoardDataBean;

public class MemberDBBean {
	private SqlSession session=SqlMapClient.getSession();

	public List<MemberDataBean> getCurrentMember(int trip_id) {
		List<MemberDataBean> memberList=session.selectList("user.getCurrentMember", trip_id);
		for(MemberDataBean user:memberList) {
			user.setUser_name((String)session.selectOne("user.getUserName", user.getUser_id()));
		}
		return memberList;
	}
	
	public String getUserName(String user_id) {
		return session.selectOne("user.getUserName", user_id);
	}
	
	public int addTripMember(Map<String, String> addMemberMap) {
		return session.update("user.addTripMember", addMemberMap);
	}	
	public int isMember(Map<String, String> delMemberMap) {
		return session.selectOne("user.isMember2", delMemberMap);
	}
	public int delTripMember(MemberDataBean memberDto) {
		return session.delete("user.delTripMember", memberDto);
	}
	
	public boolean isTBMember(BoardDataBean tbDto) {
		int count=session.selectOne("board.isTBMember",tbDto);
		if(count>0)return true;
		else return false;
	}
	
	public List<TripDataBean> getMemInfoList(int board_no) {
		List<TripDataBean> memNumList=new ArrayList<TripDataBean>();
		List<Integer> tripIds=session.selectList("location.getTripIds", board_no);
		
		if(tripIds.size()>0) {
			for(int trip_id:tripIds) {
				Map<String, Integer> currentTrip=new HashMap<String, Integer>();
				int memNum=session.selectOne("user.getMemberCount", trip_id);
				TripDataBean tripDto = new TripDataBean();
				tripDto.setTrip_id(trip_id);
				tripDto.setTrip_member_count(memNum);
				memNumList.add(tripDto);
			}		
		}
		return memNumList;
	}
	//error fix - parameter should be trip_id, not board_id. 
	//And I changed method name to getMember->getMembers
	public List<MemberDataBean> getMembers(int trip_id) {
		return session.selectList("user.getMembers", trip_id);
	}
	public int isMember(MemberDataBean memberDto) {
		return session.selectOne("user.isMember", memberDto);
	}
	public int addTripMember(MemberDataBean memberDto) {
		return session.selectOne("user.addTripMember", memberDto);
	}
	////////////////////////////////NEW! talysa7//////////////////////////////////
	//get only user_name values from pao_view_members for TripDataBean
	public List<String> getMemberNames(int trip_id) {
		return session.selectList("user.getMemberNames", trip_id);
	}
}
