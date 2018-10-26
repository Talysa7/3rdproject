package db;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class TripDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public TripDataBean getTrip(int tb_no) {
		TripDataBean tripDto=session.selectOne("db.getTrip", tb_no);
		return tripDto;
	}
	public void addViewCount(int tb_no) {
		session.update("db.addViewCount", tb_no);
	}
	public int deleteTripDetail(int tb_no) {
		return session.delete("db.deleteTripDetail",tb_no);
	}
	public int deleteCal(int tb_no) {
		return session.delete("db.deleteCal",tb_no);
	}
	//delete trip board-게시물 삭제
	public int deleteTrip(int tb_no) {
		int result;
		int calResult=deleteCal(tb_no);
		int detailResult=deleteTripDetail(tb_no);
		result=session.delete("db.deleteTrip",tb_no);
		return result;
	}
	//notice
	public void notice(int tb_no) {
		session.update("db.notice",tb_no);
	}
	//notice cancel
	public void noticeX(int tb_no) {
		session.update("db.noticeX",tb_no);
	}
	//게시물의 주인 판별
	public int isOwner(TripDataBean tripDto) {
		int count=session.selectOne("db.isOwner",tripDto);
		if(count!=0) {
			count=1;
		}
		return count;
	}
}
