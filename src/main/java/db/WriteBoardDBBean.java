package db;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class WriteBoardDBBean {
	private SqlSession session = SqlMapClient.getSession();
	
	public void setWriteDto(WriteDataBean writeDto) {

	}
}
