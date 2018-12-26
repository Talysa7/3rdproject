package db;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WriteCoordDataBean {
	// coord_id		WriteTripDataBean 에서 받은 selectKey -> 를 가지고 비교
	// 이하 통째로 비교해서 coord_id 있는지 확인 후, 없으면 insert / 있으면 coord_id 재사용
	@JsonIgnore
	private int coord_id;
	private String coord_name;
	private String country_code;
	private Double coord_long;
	private Double coord_lat;
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
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public Double getCoord_long() {
		return coord_long;
	}
	public void setCoord_long(Double coord_long) {
		this.coord_long = coord_long;
	}
	public Double getCoord_lat() {
		return coord_lat;
	}
	public void setCoord_lat(Double coord_lat) {
		this.coord_lat = coord_lat;
	}
	
}
