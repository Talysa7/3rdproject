package db;

//database table : pao_region
public class RegionDataBean {
	private int region_id;				//int (8), not null
	private String region_name;			//varchar (30), not null
	private int region_type;			//smallint (3), not null
	
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public int getRegion_type() {
		return region_type;
	}
	public void setRegion_type(int region_type) {
		this.region_type = region_type;
	}
}
