package db;

import java.sql.Timestamp;

public class AlbumDataBean {
	private int photo_id;
	private String photo_url;
	private Timestamp alb_reg_date;
	private int tb_no;
	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public Timestamp getAlb_reg_date() {
		return alb_reg_date;
	}
	public void setAlb_reg_date(Timestamp alb_reg_date) {
		this.alb_reg_date = alb_reg_date;
	}
	public int getTb_no() {
		return tb_no;
	}
	public void setTb_no(int tb_no) {
		this.tb_no = tb_no;
	}
}
