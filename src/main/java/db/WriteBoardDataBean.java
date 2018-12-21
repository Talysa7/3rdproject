package db;

import java.sql.Timestamp;
import java.util.List;

public class WriteBoardDataBean {
	private int board_no;			// board_no = auto_increment -> pao_trip 에서 필요로 함 / selectKey
	private String board_title;
	private String board_content;
	private int board_view_count;	// board_view_count = default / 0
	private int board_level;		// board_level = not null / user_level 확인 후 1 혹은 9
	private String board_contact;
	private Timestamp board_reg_date;
	private List<TagDataBean> tagList;
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
	public List<TagDataBean> getTagList() {
		return tagList;
	}
	public void setTagList(List<TagDataBean> tagList) {
		this.tagList = tagList;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
