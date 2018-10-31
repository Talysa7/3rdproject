package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class MemberDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	
}
