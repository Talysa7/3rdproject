package db;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WriteTripDataBean {
	@JsonIgnore
	private int board_no;	// board_no 	WrtieBoardDataBean / selectKey로 받은 값 -> 사용
	@JsonIgnore
	private int trip_id;	// trip_id 		auto_increment / selectKey로 값 받기 -> pao_coord 에서 필요로 함
	private int trip_member_count;
	private WriteCoordDataBean coordList;	// coord_id 필요
	private Timestamp start_date;
	private Timestamp end_date;
	private int coord_order;
	@JsonIgnore
	private int coord_id;
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
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
	public WriteCoordDataBean getCoordList() {
		return coordList;
	}
	public void setCoordList(WriteCoordDataBean coordList) {
		this.coordList = coordList;
	}
	public Timestamp getStart_date() {
		return start_date;
	}
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	public int getCoord_order() {
		return coord_order;
	}
	public void setCoord_order(int coord_order) {
		this.coord_order = coord_order;
	}
	public int getCoord_id() {
		return coord_id;
	}
	public void setCoord_id(int coord_id) {
		this.coord_id = coord_id;
	}
}
