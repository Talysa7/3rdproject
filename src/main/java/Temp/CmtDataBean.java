package Temp;

import java.sql.Date;

//database table : pao_comment
public class CmtDataBean {
	private int c_id;					//int (10), PK
	private String c_content;			//text (900), not null
	private Date c_reg_date;			//timestamp, not null
	//FK
	private int b_no;					//int (10), not null, on delete cascade
	private String user_id;				//varchar (20), not null
	
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public Date getC_reg_date() {
		return c_reg_date;
	}
	public void setC_reg_date(Date c_reg_date) {
		this.c_reg_date = c_reg_date;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
