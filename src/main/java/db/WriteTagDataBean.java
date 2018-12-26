package db;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WriteTagDataBean {
	private String tag_id;
	@JsonIgnore
	private String tag_value;
	@JsonIgnore
	private int tag_type;
	@JsonIgnore
	private int board_no;
	@JsonIgnore
	private String user_id;
	
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
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
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
