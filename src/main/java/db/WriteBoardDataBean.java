package db;

import java.util.List;

public class WriteBoardDataBean {
	private String board_title;
	private String board_content;
	private String board_contact;
	private String[] tagList;
	
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
	public String getBoard_contact() {
		return board_contact;
	}
	public void setBoard_contact(String board_contact) {
		this.board_contact = board_contact;
	}
	public String[] getTagList() {
		return tagList;
	}
	public void setTagList(String[] tagList) {
		this.tagList = tagList;
	}
}
