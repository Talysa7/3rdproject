package Temp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import Temp.CmtDataBean;

public class CmtDBBean {
	SqlSession session = SqlMapClient.getSession();
	// TODO : 1. CmtDBBean c_ 네이밍 된 컬럼명 바꾸는 것 항시 체크 / xml의 쿼리 포함
	// TODO : 2. xml 내 mapper namespace 주는것 체크
	
	//total comment
	public int insertComment(CmtDataBean cmtDto) {
		// TODO : CmtDBBean c_id 시퀀스 없는지?
		return session.insert("board.insertComment", cmtDto);
	}
	
	public List<CmtDataBean> getComment( int board_no ) {
		// FIXME : CmtDBBean tb_no 가 바뀐게 b_no 인것 같으니  확인 필요
		return session.selectList("board.getComment", board_no);
	}
	
	
	public int updateComment(CmtDataBean cmtDto) {
		return session.update("board.updateComment", cmtDto);
	}
	
	public int deleteComment( int c_id ) {
		return session.delete("board.deleteComment", c_id);
	}
	
	public int getCmtCount() {
		return session.selectOne("board.getCmtCount");
	}
	
	public List<CmtDataBean>getComments(Map<String,Integer>map){
		return session.selectList("board.getComments",map);
	}
}
