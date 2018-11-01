package db;

import java.util.List;

//database table : pao_coordinate
public class CoordDataBean {
	private int coord_id;				// int (8), PK, on delete set 1, 1 should be somewhere in Seoul
	private String coord_name;			// varchar (50), not null
	private String country_id;			// FK / smallint (4), not null, on delete cascade
	private double coord_long;			// double (10, 6), not null
	private double coord_lat;			// double (10, 6), not null
	private int coord_order;			// tinyint (1) / 장소들의 순서를 정해줌
	// guest parameter from pao_region_match
	private List<String> region_types;			// Array of region_type, varchar (30)
												// region types of this coordinate
	
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
	public int getCoord_order() {
		return coord_order;
	}
	public void setCoord_order(int coord_order) {
		this.coord_order = coord_order;
	}
	public List<String> getRegion_types() {
		return region_types;
	}
	public void setRegion_types(List<String> region_types) {
		this.region_types = region_types;
	}
}
