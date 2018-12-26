package db;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import db.TripDataBean;

public class TripDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	//please make getBoardTripList sql statement
	//get trips of the article
	public List<TripDataBean> getBoardTripList(int board_no) {		//modified method name to divide
		List<TripDataBean> boardTripList=session.selectList("location.getBoardTripList", board_no);
		//set members user_name to each trip, for convenience
		if(boardTripList.size()>0) {
			for(TripDataBean trip:boardTripList) {
				trip.setTrip_members(trip.getTrip_id());
			}
		}
		return boardTripList;
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
		BoardDataBean boardDto = new BoardDataBean();
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
	public int getCoordId(int trip_id) {
		return session.selectOne("board.getCoordId", trip_id);
	}
	public List<Integer> getTripIds(int board_no) {
		return session.selectList("board.getTripIds", board_no);
	}
	public List<TripDataBean> getUserTripList(String user_id) {
		List<TripDataBean> userTripList=session.selectList("user.getUserTripList", user_id);
		//set members user_name to each trip, for convenience
		List<TripDataBean> trip = new ArrayList<TripDataBean>();
		if(userTripList.size()>0) {
			for(int i=0; i<userTripList.size(); i++) {
			TripDataBean tripDto = userTripList.get(i);
			tripDto.setTrip_members(tripDto.getTrip_id());
			String thumbnail=session.selectOne("album.getThumbnail", tripDto.getBoard_no());
			tripDto.setThumbnail(thumbnail);
			trip.add(tripDto);
			}
		}
		return trip;
	}
	public List<TripDataBean> getReviewList(String user_id){
		return session.selectList("user.getReviewList", user_id);
	}
	
	//Here was a method 'isOwner' testing 'Is this user the owner of this article?'
	//This method should be moved to BoardDBBean or Handler, maybe we don't need this!
	//Do we need this? I don't think so.
		
	//////////////////////////////////////////////////////2018-11-05 이민재 트립 insert 추가 //////////////////////////////////////////////
		public int insertTrip(TripDataBean tripDto) {
			return session.insert("board.insertTrip",tripDto);
		}
//////////////////////////////////////////////////////2018-11-05 이민재 트립 insert 추가 //////////////////////////////////////////////
////////////////////////////////////////////////////장소 정보 관련 
		public List<TripDataBean> getTripListByCoord(int coord_id) {		//modified method name to divide
			List<TripDataBean> boardTripList=session.selectList("location.getTripListByCoord", coord_id);
			//set members user_name to each trip, for convenience
			if(boardTripList.size()>0) {
				for(TripDataBean trip:boardTripList) {
					trip.setTrip_members(trip.getTrip_id());
				}
			}
			return boardTripList;
		}	

}
