package db;

import java.sql.Timestamp;

//database table : pao_album
public class AlbumDataBean {
	private int photo_id;			//int (10), PK
	private String photo_url;		//varchar (200), not null
	private Timestamp photo_reg_date;
	//FK
	private String user_id;			//varchar (20), on delete cascade
	private int trip_id;				//int (10), not null, on delete cascade
	//guest value from pao_view_album
	private String user_name;
	
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Timestamp getPhoto_reg_date() {
		return photo_reg_date;
	}
	public void setPhoto_reg_date(Timestamp photo_reg_date) {
		this.photo_reg_date = photo_reg_date;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}
	
	
}
