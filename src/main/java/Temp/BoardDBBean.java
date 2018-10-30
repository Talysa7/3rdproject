package Temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import db.TagDataBean;
import db.TbDataBean;

public class BoardDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	//get count of all trip
	public int getCount() {
		return session.selectOne("db.getCount");
	}
	public void addCount(int board_no) {
		session.update("db.addCount", board_no);
	}
	
	//get one trip post by tb_no, including location and tag list
	public BoardDataBean getTb(int board_no) {
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
	public int updateBoard(TbDataBean boardDto) {
		return session.update("db.updateBoard", boardDto);
	}
	
	//trip board list
	public List<TbDataBean> getTrips(Map<String,Integer> map){
		return session.selectList("db.getTrips",map);
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
	
	public List<TbDataBean> findTripByUser(String keyword) {	// 수정필요.
		return null;
	}
	
	public List<Map<String, String>> getMemInfoList(int board_no) {	//	 얘도 수정필요.
		List<Map<String, String>> memNumList=new ArrayList<Map<String, String>>();
		List<TripDataBean> trips=session.selectList("db.getTripList", board_no);
		
		if(trips.size()>0) {
			for(TripDataBean tripDto:trips) {
				/*Map<String, String> currentTrip=new HashMap<String, String>();
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
	
	
	//얘는 연결되는 쿼리가 업서요
	public List<TbDataBean> getNextTrips(int latest) {
		//get 20 previous articles 
		return session.selectList("db.getNextTrips", latest);
	}
	
	
	
	
	public List<TbDataBean> getTripList() {	//이거 처음 10개만 불러와요? 그 이후엔 안쓰나요?
		//get 10 latest articles from db
		int start=1;
		int end=10;
		
		Map<String, Integer> tripReq=new HashMap<String, Integer>();
		tripReq.put("start", start);
		tripReq.put("end", end);
		List<TbDataBean> tripList=session.selectList("db.getTrips2", tripReq);
		
		for(TbDataBean tbDto:tripList) {
			//null exception
			if(tbDto.getUser_id().equals("")||tbDto.getUser_id()==null) {
				tbDto.setUser_id("Ex-User");
			}
			
			//locations and tags 
			List <Integer> tripIds=session.selectList("db.getTripIds", tbDto.getTb_no());
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
			tbDto.setTags(tags);
		}
		return tripList;
	}
	
	//load more Trip articles from db
	//Loading starts from last number of current page
	public List<TbDataBean> loadMoreList(int last_row) {
		int start=last_row;
		int end=start+4;
		
		Map<String, Integer> tripReq=new HashMap<String, Integer>();
		tripReq.put("start", start);
		tripReq.put("end", end);
		
		List<TbDataBean> tripList=session.selectList("db.getTrips", tripReq);
		for(TbDataBean tbDto:tripList) {
			//set Nickname instead of id
			String user_id=tbDto.getUser_id();
			String user_name;
			//if that user left
			if(user_id==null||user_id.equals("")) {
				user_name="Ex-User";
			} else {
				user_name=(String) session.selectOne("db.getUserName", user_id);
			}
			tbDto.setUser_id(user_name);
			
			//locations and tags 
			List <Integer> tripIds=session.selectList("db.getTripIds", tbDto.getTb_no());
			String[] locs=new String[tripIds.size()];
			for(int j=0; j<tripIds.size(); j++) {
				locs[j]=session.selectOne("db.getDestination", tripIds.get(j));
			}
			tbDto.setLocs(locs);
			
			List<TagDataBean> originTags=session.selectList("db.getTripTags", tbDto.getTb_no());
			String[] tags=new String[originTags.size()];
			for(int k=0; k<originTags.size(); k++) {
				tags[k]=originTags.get(k).getTag_value();
			}
			tbDto.setTags(tags);
		}
		return tripList;
	}
	// 위에 두놈은 같은 기능하는거 같은데??
}
