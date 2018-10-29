package Temp;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class UserDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	//get user_name from table pao_user
		public String getUser_name(String user_id) {
			return session.selectOne("user.getUserName", user_id);
		}
}
