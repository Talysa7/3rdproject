package db;

import java.util.List;

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
		 return session.insert("db.insertCoord",coordDto);
	}
	public int insertCal(CoordDataBean coordDto) {
		return session.insert("db.insertCal",coordDto);
	}
	public List<CoordDataBean> selectDetail(int board_no) {
		return session.selectList("db.selectDetail",board_no);
	}
	
	public List<CoordDataBean> selectCoordinate(int board_no) {
		return session.selectList("db.selectCoordinate",board_no);
	}
	
	public List<CoordDataBean> selectCountry(int board_no) {
		return session.selectList("db.selectCountry",board_no);
	}
	public CoordDataBean getTripDetail(int trip_id) {
		// TODO : return 이 
		CoordDataBean coordDto = session.selectOne("db.getCalendar", trip_id);
		CountryDataBean countryDto = new CountryDataBean();
		coordDto.setCoord_long((double)session.selectOne("db.getCoordLong", coordDto.getCoord_id()));
		coordDto.setCoord_lat((double)session.selectOne("db.getCoordLat", coordDto.getCoord_id()));
		coordDto.setCoord_order((int)session.selectOne("db.getCoordOrder", coordDto.getCoord_id()));
		countryDto.setCountry_name((String)session.selectOne("db.getCountryName", coordDto.getCoord_id()));
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
	
	public List<CoordDataBean> getMyTrips(String user_id) {
		return session.selectList("db.getMyTrips", user_id);
	}
}
