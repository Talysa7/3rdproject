package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;


public class UserDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
		public String getUser_name(String user_id) {
			return session.selectOne("user.getUserName", user_id);
		}
		
		public UserDataBean getUser( String user_id ) {
			return session.selectOne( "user.getUser", user_id );
		}
		
		public String getUserName(String user_id) { 
			return session.selectOne("user.getUserName", user_id); 
		} 
		
		public int nameCheck( String user_name ) {
			return session.selectOne( "user.nameCheck", user_name);
		}
		
		public int idCheck( String user_id ) {
			return session.selectOne( "user.idCheck", user_id);
		}
		
		public List<UserDataBean> getUsers(Map<String, Integer> map) {
			return session.selectList("user.getUsers",map);
		}

		public int getAllUserCount() {
			return session.selectOne("user.getAllUserCount");
		}
				
		public int insertUser( UserDataBean UserDto ) {
			return session.insert("user.insertUser", UserDto);
		}
		
		public int check( String user_id ) {
			return session.selectOne( "user.checkId", user_id);
		}
		
		public UserDataBean getUserEmailId(String email) { 
			return session.selectOne("user.getUserEmailId", email); 
		} 
		
		public UserDataBean getUserEmailPasswd(String email) { 
			return session.selectOne("user.getUserEmailPasswd", email); 
		} 
		
		public int EmailCheck( String email ) {
			return session.selectOne( "user.EmailCheck", email);
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
			return session.delete("user.deleteUser", user_id);
		}
		
		public int modifyUser( UserDataBean UserDto ) {
			return session.update( "user.modifyUser", UserDto );
		}
		
		public int getUserLevel(String user_id) {
			return session.selectOne("user.getUserLevel",user_id);
		}
		
		public String getUserId(String user_name) { 
			return session.selectOne("user.getUserId", user_name); 
		} 
	
