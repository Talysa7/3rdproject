package Temp;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class TripDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
}
