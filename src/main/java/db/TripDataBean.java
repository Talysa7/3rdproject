package db;

import java.sql.Timestamp;

public class TripDataBean {
	public int tb_no;
	public String user_id;
	public String tb_title;
	public String tb_content;
	public Timestamp tb_reg_date;
	public int tb_v_count;
	public int tb_m_num;
	public int tb_notice;
	public String tb_talk;
	public int getTb_no() {
		return tb_no;
	}
	public void setTb_no(int tb_no) {
		this.tb_no = tb_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTb_title() {
		return tb_title;
	}
	public void setTb_title(String tb_title) {
		this.tb_title = tb_title;
	}
	public String getTb_content() {
		return tb_content;
	}
	public void setTb_content(String tb_content) {
		this.tb_content = tb_content;
	}
	public Timestamp getTb_reg_date() {
		return tb_reg_date;
	}
	public void setTb_reg_date(Timestamp tb_reg_date) {
		this.tb_reg_date = tb_reg_date;
	}
	public int getTb_v_count() {
		return tb_v_count;
	}
	public void setTb_v_count(int tb_v_count) {
		this.tb_v_count = tb_v_count;
	}
	public int getTb_m_num() {
		return tb_m_num;
	}
	public void setTb_m_num(int tb_m_num) {
		this.tb_m_num = tb_m_num;
	}
	public int getTb_notice() {
		return tb_notice;
	}
	public void setTb_notice(int tb_notice) {
		this.tb_notice = tb_notice;
	}
	public String getTb_talk() {
		return tb_talk;
	}
	public void setTb_talk(String tb_talk) {
		this.tb_talk = tb_talk;
	}
}
