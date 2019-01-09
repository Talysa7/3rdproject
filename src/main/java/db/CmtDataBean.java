package db;

import java.sql.Date;

//database table : pao_comment
public class CmtDataBean {
	private int comment_id;					//int (10), PK
	private String comment_content;			//text (900), not null
	private Date comment_reg_date;			//timestamp, not null
	//FK
	private int board_no;					//int (10), not null, on delete cascade
	private String user_id;				//varchar (20), not null
	private String user_name;
	
	
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_reg_date() {
		return comment_reg_date;
	}
	public void setComment_reg_date(Date comment_reg_date) {
		this.comment_reg_date = comment_reg_date;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
