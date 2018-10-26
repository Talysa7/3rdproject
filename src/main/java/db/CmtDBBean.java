package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;



public class CmtDBBean {
	SqlSession session=SqlMapClient.getSession();
	//total comment
	public int insertComment(CmtDataBean cmtDto) {
		return session.insert("db.insertComment", cmtDto);
	}
	
	public List<CmtDataBean> getComment( int tb_no ) {
		return session.selectList("db.getComment", tb_no);
	}
	
	public int updateComment(CmtDataBean cmtDto) {
		return session.update("db.updateComment", cmtDto);
	}
	
	public int deleteComment( int c_id ) {
		return session.delete("db.deleteComment", c_id);
	}
	
	public int getCount() {
		return session.selectOne("db.getCmtCount");
	}
	//
	public List<CmtDataBean>getComments(Map<String,Integer>map){
		return session.selectList("db.getComments",map);
	}
}
