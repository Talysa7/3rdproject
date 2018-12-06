package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bean.SqlMapClient;

public class CmtDBBean {
	SqlSession session = SqlMapClient.getSession();
	
	//total comment
	public int insertComment(CmtDataBean cmtDto) {		
		return session.insert("board.insertComment", cmtDto);
	}
	
	public List<CmtDataBean> getComment( int board_no ) {
		return session.selectList("board.getComment", board_no);
	}
	
	
	public int updateComment(CmtDataBean cmtDto) {
		return session.update("board.updateComment", cmtDto);
	}
	
	public int deleteComment( int comment_id ) {
		return session.delete("board.deleteComment", comment_id);
	}
	
	public int getCmtCount() {
		return session.selectOne("board.getCmtCount");
	}
	
	public List<CmtDataBean>getComments(Map<String,Integer>map){
		return session.selectList("board.getComments",map);
	}
//	send jsonarray
	public void insertCommentLog(CmtDataBean cmtDto) {
		JSONObject wrapObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("comment_id", cmtDto.getComment_id());
		jsonObject.put("comment_content", cmtDto.getComment_content().toString());
		jsonObject.put("comment_reg_date", cmtDto.getComment_reg_date());
		jsonObject.put("board_no", cmtDto.getBoard_no());
		jsonObject.put("user_id", cmtDto.getUser_id().toString());
		jsonObject.put("user_name", cmtDto.getUser_name().toString());
		jsonArray.add(jsonObject);
		
		wrapObject.put("result",jsonArray);
		wrapObject.put("log_type", 4);
		//FIXME : 확인 필요
		System.out.println(wrapObject);
	}

}
