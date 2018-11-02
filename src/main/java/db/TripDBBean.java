package db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import db.TripDataBean;

public class TripDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	//get trips of the article
	public List<TripDataBean> getBoardTripList(int board_no) {		//modified method name to divide
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
	
	/////////////////////////////////NEW-talysa7///////////////////////////////////
	public List<TripDataBean> getUserTripList(String user_id) {
		List<TripDataBean> userTripList=session.selectList("user.getUserTripList", user_id);
		//set members user_name to each trip, for convenience
		if(userTripList.size()>0) {
			for(TripDataBean trip:userTripList) {
				trip.setTrip_members(trip.getTrip_id());
			}
		}
		return userTripList;
	}
}
