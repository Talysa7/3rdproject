package db;

import java.sql.Date;

//database view(with user_name) : pao_view_trip
//database table : pao_trip
public class TripDataBean {
	private int trip_id;										//int (10), PK, not null, on delete cascade
	private int trip_member_count;					//smallint (4), not null
	private Date start_date;								//timestamp, not null
	private Date end_date;								//timestamp, not null
	//FK
	private int board_no;											//int (10), not null
	private int coord_id;									//int (8), not null
	//guest value from pao_user
	private String user_name;							//varchar (20), not null
	
	public int getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}
	public int getTrip_member_count() {
		return trip_member_count;
	}
	public void setTrip_member_count(int trip_member_count) {
		this.trip_member_count = trip_member_count;
	}
	public int getCoord_id() {
		return coord_id;
	}
	public void setCoord_id(int coord_id) {
		this.coord_id = coord_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
