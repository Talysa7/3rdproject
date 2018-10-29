package Temp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class TagDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	public List<String> getB_tags(int b_no) {
		return session.selectList("board.getBoardTags", b_no);
	}
	public List<String> getUser_tags(String user_id) {
		return session.selectList("user.getUserTags", user_id);
	}
}
