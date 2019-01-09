package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class BoardDBBean {
	private SqlSession session=SqlMapClient.getSession();

	//get count of all trip
	public int getPostsCount() {
		return session.selectOne("board.getPostsCount");
	}
	public void addViewCount(int board_no) {
		session.update("board.addViewCount", board_no);
	}

	//get one trip post by tb_no, including location and tag list
	public BoardDataBean getPost(int board_no) {
		BoardDataBean boardDto=session.selectOne("board.getPost", board_no);
		//if that user left
		if(boardDto.getUser_id()==null||boardDto.getUser_id().equals("")) {
			boardDto.setUser_name("Ex-User");
		}
		//set tags
		List<TagDataBean> board_tags=session.selectList("tag.getBoardTags", board_no);
		boardDto.setBoard_tags(board_tags);
		//set trips
		List<TripDataBean> tripLists=session.selectList("location.getBoardTripList", board_no);
		for(TripDataBean trip:tripLists) {
			trip.getTrip_members(trip.getTrip_id());
		}
		boardDto.setTripLists(tripLists);

		return boardDto;
	}

	public int insertBoard_no(BoardDataBean boardDto) {
		return session.insert("board.insertBoard_no",boardDto);
	}

	public String getUserId(String user_name) {
		return session.selectOne("db.getUserId", user_name);
	}
	public int updateBoard(BoardDataBean boardDto) {
		return session.update("db.updateBoard", boardDto);
	}

	public int deletePost(int board_no) {
		return session.delete("board.deletePost", board_no);
	}

	public boolean isMember(BoardDataBean boardDto) {
		int count=session.selectOne("db.isMember",boardDto);
		if(count>0)return true;
		else return false;
	}

	public List<BoardDataBean> findPostByKeyword(String keyword) {	
		//for where~like searching 
		keyword="%"+keyword+"%";
		List<BoardDataBean> postList=session.selectList("board.getPostByKeyword", keyword);
		for(BoardDataBean boardDto:postList) {
			//set Trips
			List<TripDataBean> tripLists=session.selectList("location.getTripListByBoardNo", boardDto.getBoard_no());
			boardDto.setTripLists(tripLists);
			//set Tags
			List<TagDataBean> board_tags=session.selectList("tag.getPostTags", boardDto.getBoard_no());
			boardDto.setBoard_tags(board_tags);
		}
		return postList;
	}

	public List<BoardDataBean> findPostByUser(String keyword) {
		//for where~like searching 
		keyword="%"+keyword+"%";
		List<BoardDataBean> postList=session.selectList("board.getPostByUserName", keyword);
		for(BoardDataBean boardDto:postList) {
			//set Trips
			List<TripDataBean> tripLists=session.selectList("location.getTripListByBoardNo", boardDto.getBoard_no());
			boardDto.setTripLists(tripLists);
			//set Tags
			List<TagDataBean> board_tags=session.selectList("tag.getPostTags", boardDto.getBoard_no());
			boardDto.setBoard_tags(board_tags);
		}
		return postList;
	}

	public List<Map<String, String>> getMemInfoList(int board_no) {	//	 얘도 수정필요.
		List<Map<String, String>> memNumList=new ArrayList<Map<String, String>>();
		List<TripDataBean> trips=session.selectList("db.getTripList", board_no);

		if(trips.size()>0) {
			for(TripDataBean tripDto:trips) {
				/*Map<String, String> currentTrip=new HashMap<String, String>();	사람수 가져오는건데 각 세부일정당으로 바뀌어서 보여줄수가 없음.
				int memNum=session.selectOne("db.getMemberCount", trip_id);
				String td_trip_id_string=""+trip_id;
				String memNum_string=""+memNum;

				currentTrip.put("td_trip_id", td_trip_id_string);
				currentTrip.put("memNum", memNum_string);
				memNumList.add(currentTrip);*/
			}
		}

		return memNumList;
	}

	//select from pao_view_board with rowNumber
	//by talysa7
	public List<BoardDataBean> getPostList(int rowNumber, int postPerPage) {
		Map<String, Integer> tripReq=new HashMap<String, Integer>();
		if(rowNumber-1>0) {
			tripReq.put("startRowNumber", (rowNumber-1)*postPerPage);	
		}else {
			tripReq.put("startRowNumber", 0);	
		}
		tripReq.put("endRowNumber", postPerPage);
		
		List<BoardDataBean> postList=session.selectList("board.getPostList", tripReq);
		//user_name null exception
		if(postList.size()>0) {
			for(BoardDataBean boardDto:postList) {
				if(boardDto.getUser_id().equals("")||boardDto.getUser_id()==null) {
					boardDto.setUser_name("Ex-User");
				}
				//set Trips
				List<TripDataBean> tripLists=session.selectList("location.getTripListByBoardNo", boardDto.getBoard_no());
				boardDto.setTripLists(tripLists);
				//set Tags
				List<TagDataBean> board_tags=session.selectList("tag.getPostTags", boardDto.getBoard_no());
				boardDto.setBoard_tags(board_tags);
				
				//set Thumbnail
				String thumbnail=session.selectOne("album.getThumbnail", boardDto.getBoard_no());
				boardDto.setThumbnail(thumbnail);
			}
		}
		return postList;
	}
	public BoardDataBean getBoard(int board_no) {
		return session.selectOne("board.getBoard", board_no);
	}
	
	public List<BoardDataBean> advanceSearchByDate(Map<String,String> searchMap){
		return session.selectList("board.advanceSearchByDate",searchMap);
	}
	
	public List<BoardDataBean> advanceSearchByPeriod(Map<String,String> searchMap){
		return session.selectList("board.advanceSearchByPeriod",searchMap);
	}
	
	public List<BoardDataBean> advanceSearchByTag(Map<String, Object> searchMap){
		return session.selectList("board.advanceSearchByTag",searchMap);
	}
	
	public List<BoardDataBean> advanceSearchBySite(Map<String, Object> searchMap){
		return session.selectList("board.advanceSearchBySite",searchMap);
	}
	
	public List<BoardDataBean> advanceSearchByDatePeriod(Map<String, Object> searchMap){
		return session.selectList("board.advanceSearchByDatePeriod",searchMap);
	}
	
	public int countCoord (WriteCoordDataBean coordDto) {
		return session.selectOne("board.countCoord", coordDto);
	}
	public int getCoord_id (WriteCoordDataBean coordDto) {
		return session.selectOne("board.getCoord_id", coordDto);
	}
	public int insertCoord (WriteCoordDataBean coordDto) {
		return session.insert("board.insertCoord", coordDto);
	}
	public int insertBoardDto (WriteBoardDataBean boardDto) {
		return session.insert("board.insertBoardDto", boardDto);
	}
	public int insertTripDto (WriteTripDataBean tripDto) {
		return session.insert("board.insertTripDto", tripDto);
	}
	public int insertTagList (Map<String, Object> tag_map) {
		return session.insert("board.insertTagList", tag_map);
	}
	
}
