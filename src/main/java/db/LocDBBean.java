/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class LocDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public void getLoc() {
	}
	public void setLoc() {
	}
	public void delLoc() {
	}
	//when knowing country code, insert coordinate and if inserting data succeed, return coord_id
	public int insertCoord(LocDataBean locDto) {
		 return session.insert("db.insertCoord",locDto);
	}
	public int insertCal(LocDataBean locDto) {
		return session.insert("db.insertCal",locDto);
	}
	public List<LocDataBean> selectDetail(int tb_no) {
		return session.selectList("db.selectDetail",tb_no);
	}
	
	public List<LocDataBean> selectCoordinate(int tb_no) {
		return session.selectList("db.selectCoordinate",tb_no);
	}
	
	public List<LocDataBean> selectCountry(int tb_no) {
		return session.selectList("db.selectCountry",tb_no);
	}
	public LocDataBean getTripDetail(int td_trip_id) {
		LocDataBean locDto=session.selectOne("db.getCalendar", td_trip_id);
		locDto.setCoord_long((double)session.selectOne("db.getCoordLong", locDto.getCoord_id()));
		locDto.setCoord_lat((double)session.selectOne("db.getCoordLat", locDto.getCoord_id()));
		locDto.setCoord_order((int)session.selectOne("db.getCoordOrder", locDto.getCoord_id()));
		locDto.setCountry_name((String)session.selectOne("db.getCountryName", locDto.getCoord_id()));
		return locDto;
	}
	
	//get destination countriy's name of some trip
	public String getPhotoLoc(int tb_no) {
		//get trip ids
		List<Integer> tripIds=session.selectList("db.getTripIds", tb_no);
		String locs="";
		//get country name with trip ids
		if(tripIds.size()>0) {
			for(int i=0; i<tripIds.size(); i++) {
				int td_trip_id=tripIds.get(i);
				String country=session.selectOne("db.getTripCountry", td_trip_id);
				if(i==tripIds.size()-1) {
					locs=locs+country;
				} else {
					locs=locs+", "+country;
				}
			}
		}
		return locs;
	}
	
	public List<LocDataBean> getMyTrips(String user_id) {
		return session.selectList("db.getMyTrips", user_id);
	}
}