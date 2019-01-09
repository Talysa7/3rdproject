package db;

//database table : pao_country
public class CountryDataBean {
	private int country_id;					//smallint (4), PK, on delete cascade
	private String country_name;			//varchar (50), not null
	//FK
	private int region_id;					//int (8), not null, on delete cascade
	
	private String country_code;
	private String country_eng_name;
	private String country_kor_name;
	
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getCountry_eng_name() {
		return country_eng_name;
	}
	public void setCountry_eng_name(String country_eng_name) {
		this.country_eng_name = country_eng_name;
	}
	public String getCountry_kor_name() {
		return country_kor_name;
	}
	public void setCountry_kor_name(String country_kor_name) {
		this.country_kor_name = country_kor_name;
	}
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
