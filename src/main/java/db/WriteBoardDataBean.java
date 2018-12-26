package db;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WriteBoardDataBean {
	@JsonIgnore
	private int board_no;			// board_no = auto_increment -> pao_trip 에서 필요로 함 / selectKey
	private String board_title;
	private String board_content;
	@JsonIgnore
	private int board_view_count;	// board_view_count = default / 0
	@JsonIgnore
	private int board_level;		// board_level = not null / user_level 확인 후 1 혹은 9
	private String board_contact;
	@JsonIgnore
	private Timestamp board_reg_date;
	private String[] tagList;
	private String user_id;
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_view_count() {
		return board_view_count;
	}
	public void setBoard_view_count(int board_view_count) {
		this.board_view_count = board_view_count;
	}
	public int getBoard_level() {
		return board_level;
	}
	public void setBoard_level(int board_level) {
		this.board_level = board_level;
	}
	public String getBoard_contact() {
		return board_contact;
	}
	public void setBoard_contact(String board_contact) {
		this.board_contact = board_contact;
	}
	public Timestamp getBoard_reg_date() {
		return board_reg_date;
	}
	public void setBoard_reg_date(Timestamp board_reg_date) {
		this.board_reg_date = board_reg_date;
	}
	public String[] getTagList() {
		return tagList;
	}
	public void setTagList(String[] tagList) {
		this.tagList = tagList;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
