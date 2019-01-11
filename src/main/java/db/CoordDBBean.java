package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;


public class CoordDBBean {
	SqlSession session=SqlMapClient.getSession();

	public void getLoc() {
	}
	public void setLoc() {
	}
	public void delLoc() {
	}
	//when knowing country code, insert coordinate and if inserting data succeed, return coord_id
	public int insertCoord(CoordDataBean coordDto) {
		 return session.insert("location.insertCoord",coordDto);
	}
	public int insertCal(CoordDataBean coordDto) {
		return session.insert("location.insertCal",coordDto);
	}
//	FIXME : calendar 테이블이 없어짐 / 기능 여부 따져봐야함
//	public List<CoordDataBean> selectDetail(int board_no) {
//		return session.selectList("location.selectDetail",board_no);
//	}
//	public List<CoordDataBean> selectCoordinate(int board_no) {
//		return session.selectList("location.selectCoordinate",board_no);
//	}

//	테이블 교체로 인해 쿼리와 DB 맞지 않음
//	public List<CoordDataBean> selectCountry(int board_no) {
//		return session.selectList("db.selectCountry",board_no);
//	}
	

	public List<CoordDataBean> selectCountry(int board_no) {
		return session.selectList("db.selectCountry",board_no);
	}
	public CoordDataBean getTripDetail(int trip_id) {
		CoordDataBean coordDto = session.selectOne("location.getCalendar", trip_id);
		return coordDto;
	}

	
	//get destination countriy's name of some trip
	public String getPhotoLoc(int board_no) {
		//get trip ids
		List<Integer> tripIds=session.selectList("db.getTripIds", board_no);
		String locs="";
		//get country name with trip ids
		if(tripIds.size()>0) {
			for(int i=0; i<tripIds.size(); i++) {
				int trip_id=tripIds.get(i);
				String country=session.selectOne("db.getTripCountry", trip_id);
				if(i==tripIds.size()-1) {
					locs=locs+country;
				} else {
					locs=locs+", "+country;
				}
			}
		}
		return locs;
	}
	//instead of this, getUserTripList
	public List<CoordDataBean> getMyTrips(String user_id) {
		return session.selectList("db.getMyTrips", user_id);
	}

	public List<CoordDataBean> getCoordDetail(int trip_id) {
		return session.selectList("location.getCoordDetail", trip_id);
	}

	public CoordDataBean getCoordinate(int coord_id) {
		return session.selectOne("location.getCoordinate", coord_id);
	}
	
	///////////////////////////////////////////////////////자동완성 관련 메소드 추가 및 추가 기능  , 이민재, 2018-11-05///////////////////////////////////
	public List<CoordDataBean> coordAutoComplete(String coord_name){
		return session.selectList("location.coordAutoComplete", coord_name);
	}
	
	public List<CoordDataBean> checkCoordName(String coord_name){
		return session.selectList("location.checkCoordName", coord_name);
	}
	///////////////////////////////////////////////////////자동완성 관련 메소드 추가 , 이민재, 2018-11-05///////////////////////////////////

	////////////////////////////////////////talysa7/////////////////////////////////////////////////

	////////////////////////////////
	public List<CoordDataBean> coordAll(){
		return session.selectList("location.coordAll");
	}
	
	public String coordIdtoCoordName1(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName1", sendNum);
	}
	public String coordIdtoCoordName2(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName2", sendNum);
	}
	public String coordIdtoCoordName3(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName3", sendNum);
	}
	public String coordIdtoCoordName4(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName4", sendNum);
	}
	public String coordIdtoCoordName5(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName5", sendNum);
	}
	public String coordIdtoCoordName6(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName6", sendNum);
	}
	public String coordIdtoCoordName7(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName7", sendNum);
	}
	public String coordIdtoCoordName8(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName8", sendNum);
	}
	public String coordIdtoCoordName9(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName9", sendNum);
	}
	public String coordIdtoCoordName10(Map<String, Integer> sendNum){
		return session.selectOne("location.coordNumToName10", sendNum);
	}
}
