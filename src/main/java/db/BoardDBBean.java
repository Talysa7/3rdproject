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
	public BoardDataBean getBoard(int board_no) {
		//original trip data
		BoardDataBean boardDto=session.selectOne("db.getTrip", board_no);
		//set Nickname instead of id
		String user_id=boardDto.getUser_id();
		String user_name;
		//if that user left
		if(user_id==null||user_id.equals("")) {
			user_name="Ex-User";
		} else {
			user_name=(String) session.selectOne("db.getUserName", user_id);
		}
		boardDto.setUser_id(user_name);
		
		//trip detail	TODO:관련부분 JSP수정필요. 원래 ID만 줬지만 지금은 전체를 통째로 던져줌.
		List<TripDataBean> tripLists=session.selectList("db.getTripList", board_no);
		boardDto.setTripLists(tripLists);
		
		return boardDto;
	}
	
	public int insertBoard_no(BoardDataBean boardDto) {
	      return session.insert("db.insertBoard_no",boardDto);
	}
	
	public int insertTrip(BoardDataBean boardDto) {
		return session.insert("db.insertTrip",boardDto);
	}
	
	public String getUserId(String user_name) {
		return session.selectOne("db.getUserId", user_name);
	}
	public int updateBoard(BoardDataBean boardDto) {
		return session.update("db.updateBoard", boardDto);
	}
	
	//trip board list
	public List<BoardDataBean> getTrips(Map<String,Integer> map){
		return session.selectList("board.getTrips", map);
	}
	
	public int deleteTrip(int board_no) {
		return session.delete("db.deleteTrip", board_no);
	}
	
	public boolean isMember(BoardDataBean boardDto) {
		int count=session.selectOne("db.isMember",boardDto);
		if(count>0)return true;
		else return false;
	}
	
	public List<BoardDataBean> findTripByKeyword(String keyword) {	//	수정필요.
		return null;
	}
	
	public List<BoardDataBean> findTripByUser(String keyword) {	// 수정필요.
		return null;
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
	
	
	public List<BoardDataBean> getTripList(int startVal , int endVal) {	//이거 처음 10개만 불러와요? 그 이후엔 안쓰나요?
		//get 10 latest articles from db
		int start=startVal;
		int end=endVal;
		
		Map<String, Integer> tripReq=new HashMap<String, Integer>();
		tripReq.put("start", start);
		tripReq.put("end", end);
		List<BoardDataBean> BoardList=session.selectList("db.getTrips2", tripReq);
		
		for(BoardDataBean boardDto:BoardList) {
			//null exception
			if(boardDto.getUser_id().equals("")||boardDto.getUser_id()==null) {
				boardDto.setUser_id("Ex-User");
			}
			
			//locations and tags 
			//뷰에서 끌어다 쓰게 수정. 나라 이름까지 받을 필요가있음.
			/*List <Integer> tripIds=session.selectList("db.getTripIds", boardDto.getBoard_no());
			String[] locs=new String[tripIds.size()];
			for(int j=0; j<tripIds.size(); j++) {
				String dest=session.selectOne("db.getDestination", tripIds.get(j));
				boolean addOrNot=true;
				if (locs.length>0) {
					for(int c=0; c<locs.length; c++) {
						if(dest.equals(locs[c]) && locs[c]!=null) {
							addOrNot=false;
						}
					}
				}
				if(addOrNot) {
					locs[j]=dest;
				}
			}
			tbDto.setLocs(locs);
			
			List<TagDataBean> originTags=session.selectList("db.getTripTags", tbDto.getTb_no());
			String[] tags=new String[originTags.size()];
			for(int k=0; k<originTags.size(); k++) {
				tags[k]=originTags.get(k).getTag_value();
			}
			tbDto.setTags(tags);*/
		}
		return null;
	}
	

}
