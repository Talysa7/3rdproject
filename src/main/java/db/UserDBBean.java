/*
 * !!! 占쏙옙占실삼옙占쏙옙 !!!
 * 占쏙옙占쏙옙 void, 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌨소듸옙 占싱몌옙占쏙옙 占쏙옙占쏙옙占쌔듸옙 占쏙옙占쏙옙.
 * 占쌨소드마占쏙옙 占쏙옙占쏙옙 占쏙옙, 占쏙옙占쏙옙 占싯아쇽옙 채占쏙옙 占쏙옙占쏙옙 占쏙옙.
 */

package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class UserDBBean {
	private SqlSession session=SqlMapClient.getSession();

	public List<UserDataBean> getUsers(Map<String, Integer> map) {
		return session.selectList("db.getUsers",map);
	}
	public int getCount() {
		return session.selectOne("db.getUCount");
	}
	public int insertUser( UserDataBean UserDto ) {
		return session.insert("db.insertUser", UserDto);
	}
	
	public int insertUser_tag(Map<String, String> map) {
		return session.update("db.insertUser_tag", map);		
	}
	
	public int check( String user_id ) {
		return session.selectOne( "db.checkId", user_id);
	}
	
	public UserDataBean getUserEmailId(String email) { 
		return session.selectOne("db.getUserEmailId", email); 
	} 
	
	public UserDataBean getUserEmailPasswd(String email) { 
		return session.selectOne("db.getUserEmailPasswd", email); 
	} 
	
	public int EmailCheck( String email ) {
		return session.selectOne( "db.EmailCheck", email);
	}
	public int nameCheck( String user_name ) {
		return session.selectOne( "db.nameCheck", user_name);
	}
	public int idCheck( String user_id ) {
		return session.selectOne( "db.idCheck", user_id);
	}
	public int check( String user_id, String passwd ) {
		int result = 0;		
		if( check( user_id ) > 0 ) {
			// 아이디가 있다
			UserDataBean UserDto = getUser( user_id );
			if( passwd.equals( UserDto.getPasswd() ) ) {
				result = 1;
			} else {
				result = -1;
			}				
		} else {
			// 아이디가 없다
			result = 0;				
		}
		return result;
	}
	public int deleteUser( String user_id ) {
		return session.delete("db.deleteUser", user_id);
	}
	public UserDataBean getUser( String user_id ) {
		return session.selectOne( "db.getUser", user_id );
	}
	public int modifyUser( UserDataBean UserDto ) {
		return session.update( "db.modifyUser", UserDto );
	}
	public int getUserLevel(String user_id) {
		return session.selectOne("db.getUserLevel",user_id);
	}public String getUserId(String user_name) { 
		return session.selectOne("db.getUserId", user_name); 
	} 
	public String getUserName(String user_id) { 
		return session.selectOne("db.getUserName", user_id); 
	} 
	
	public int deleteMember( String user_id ) {
		return session.delete("Member.deleteUser", user_id);
	}
	
	public List<UserDataBean> getCurrentMember(String td_trip_id) {
		List<UserDataBean> memberList=session.selectList("db.getCurrentMember", td_trip_id);
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
		return session.selectOne("db.isMember2", delMemberMap);
	}

}
