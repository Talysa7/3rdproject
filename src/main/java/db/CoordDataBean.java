package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//database table : pao_coordinate
public class CoordDataBean {
	private int coord_id;				// int (8), PK, on delete set 1, 1 should be somewhere in Seoul
	private String coord_name;			// varchar (50), not null
	private String country_id;			// FK / smallint (4), not null, on delete cascade
	private double coord_long;			// double (10, 6), not null
	private double coord_lat;			// double (10, 6), not null
	// guest parameter from pao_region_match
	private List<String> region_types;			// Array of region_type, varchar (30)
												// region types of this coordinate
	private String country_code;			//varchar (2)
	//using this to show place information
	private List<CoordReviewDataBean> coordReview;	
	private List<TagDataBean> boardtags;
	private String country_name;
	private double average;
	private Map<String, Integer> map;
	
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public int getCoord_id() {
		return coord_id;
	}
	public void setCoord_id(int coord_id) {
		this.coord_id = coord_id;
	}
	public String getCoord_name() {
		return coord_name;
	}
	public void setCoord_name(String coord_name) {
		this.coord_name = coord_name;
	}
	public double getCoord_long() {
		return coord_long;
	}
	public void setCoord_long(double coord_long) {
		this.coord_long = coord_long;
	}
	public double getCoord_lat() {
		return coord_lat;
	}
	public void setCoord_lat(double coord_lat) {
		this.coord_lat = coord_lat;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public List<String> getRegion_types(int coord_id) {
		RegionDBBean regionDao = new RegionDBBean();
		region_types = regionDao.getRegions(coord_id);
		return region_types;
	}
	public List<String> getRegion_types() {
		return region_types;
	}
	public void setRegion_types(List<String> region_types) {
		this.region_types = region_types;
	}
	public List<CoordReviewDataBean> getCoordReview() {
		return coordReview;
	}
	public void setCoordReview(List<CoordReviewDataBean> list) {
		this.coordReview = list;
	}
	public void setCoordReview(int coord_id) {
		CoordReviewDBBean coordReviewDao=new CoordReviewDBBean();
		setCoordReview(coordReviewDao.coordReview(coord_id));
	}
	public List<TagDataBean> getBoardtags() {
		return boardtags;
	}
	public void setBoardtags(List<TagDataBean> boardtags) {
		this.boardtags = boardtags;
	}
	public void setBoardtags(int coord_id) {
		TripDBBean tripDto = new TripDBBean();
		List<TripDataBean>trip = tripDto.getTripListByCoord(coord_id);
		List<TagDataBean> boardTags = new ArrayList<TagDataBean>();
		for(int i=0; i<trip.size(); i++) {
			int board_no = trip.get(i).getBoard_no();
			BoardDBBean board = new BoardDBBean();
			BoardDataBean boardDto = board.getPost(board_no);
			List<TagDataBean> tags = boardDto.getBoard_tags();
			boardTags.addAll(tags);
		}
		setBoardtags(boardTags);
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(Double divide) {
		this.average = divide;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
}
