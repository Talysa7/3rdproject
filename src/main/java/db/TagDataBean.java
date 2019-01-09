package db;

//database table : pao_tag
public class TagDataBean {
	private int tag_id;					//int (8), PK, on delete cascade
	private String tag_value;			//varchar (20), UK, not null
	private int tag_type;				//smallint (3), not null
	//guest value for pao_view_board_tags
	private int board_no;
	//guest value for pao_view_user_tags
	private String user_id;
	
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
