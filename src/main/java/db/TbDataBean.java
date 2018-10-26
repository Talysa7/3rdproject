package db;

import java.sql.Timestamp;

public class TbDataBean extends TripDataBean {
	private int tb_no;
	private String user_id;
	private String tb_title;
	private String tb_content;
	private Timestamp tb_reg_date;
	private int tb_v_count;
	private int tb_m_num;
	private int tb_notice;
	private String tb_talk;
	private int td_trip_id;
	private int[] td_trip_ids;
	private String[] locs;
	private String[] tags;
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
	public int getTd_trip_id() {
		return td_trip_id;
	}
	public void setTd_trip_id(int td_trip_id) {
		this.td_trip_id = td_trip_id;
	}
	public String[] getLocs() {
		return locs;
	}
	public void setLocs(String[] locs) {
		this.locs = locs;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public int[] getTd_trip_ids() {
		return td_trip_ids;
	}
	public void setTd_trip_ids(int[] td_trip_ids) {
		this.td_trip_ids = td_trip_ids;
	}
}
