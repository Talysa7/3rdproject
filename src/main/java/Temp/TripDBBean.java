package Temp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import db.TripDataBean;

public class TripDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	//get trips of the article
	public List<TripDataBean> getTripList(int board_no) {
		return session.selectList("board.getTripList", board_no);
	}
	//get a specific trip
	public TripDataBean getTrip(int trip_id) {
		return session.selectOne("board.getTrip", trip_id);
	}
	//New! add new trip, this bean parameter must have every value!
	public int addTrip(TripDataBean tripDto) {
		return session.update("board.addTrip", tripDto);
	}
	//Rename, deleteTripDetail->deleteTrip
	//Delete each Trip by trip_ip
	public int deleteTrip(int trip_id) {
		return session.delete("board.deleteTrip", trip_id);
	}
	//New! update a trip, this bean parameter must have every value
	public int updateTrip(TripDataBean tripDto) {
		return session.update("board.updateTrip", tripDto);
	}
	//I changed notice to setBoardLevel, that value has been changed to board_level
	public void setBoardLevel(int board_no, int board_level) {
		//Bean for parameter
		BoardDataBean boardDto=new BoardDataBean();
		boardDto.setBoard_no(board_no);
		boardDto.setBoard_level(board_level);
		session.update("board.setBoardLevel", boardDto);
	}
	
	//I removed 'deleteCal(from gg_calendar), that table merged into Trip!
	
	//I removed 'deleteTrip(from gg_tb)', because this is the TripDBBean!
	
	//I removed 'noticeX', we can set level with 'setBoardLevel' method.
	
	//Why is this here? Anyway I fixed it.
		public void addViewCount(int board_no) {
			session.update("board.addBoardViewCount", board_no);
		}
	
	//Here was a method 'isOwner' testing 'Is this user the owner of this article?'
	//This method should be moved to BoardDBBean or Handler, maybe we don't need this!
	//Do we need this? I don't think so.
}
