package db;

//database table : pao_country
public class CountryDataBean {
	private int country_id;					//smallint (4), PK, on delete cascade
	private String country_name;			//varchar (50), not null
	//FK
	private int region_id;					//int (8), not null, on delete cascade
	
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
}
