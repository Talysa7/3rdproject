package db;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class BoardDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	
}
