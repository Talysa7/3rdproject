package db;

import java.sql.Timestamp;

public class CmtDataBean {
	private int c_id;
	private int tb_no;
	private String user_id;
	private String user_name;
	private String c_content;
	private Timestamp c_reg_date;
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getTb_no() {
		return tb_no;
	}
	public void setTb_no(int tb_no) {
		this.tb_no = tb_no;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public Timestamp getC_reg_date() {
		return c_reg_date;
	}
	public void setC_reg_date(Timestamp c_reg_date) {
		this.c_reg_date = c_reg_date;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
