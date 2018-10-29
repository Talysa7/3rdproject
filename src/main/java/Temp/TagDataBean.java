package Temp;

//database table : pao_tag
public class TagDataBean {
	private int tag_id;					//int (8), PK, on delete cascade
	private String tag_value;			//varchar (20), UK, not null
	private int tag_type;				//smallint (3), not null
	
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_value() {
		return tag_value;
	}
	public void setTag_value(String tag_value) {
		this.tag_value = tag_value;
	}
	public int getTag_type() {
		return tag_type;
	}
	public void setTag_type(int tag_type) {
		this.tag_type = tag_type;
	}
}
