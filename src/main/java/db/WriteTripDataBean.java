package db;

public class WriteTripDataBean {
	private String trip_member_count;
	private WriteCoordDataBean coordList;
	private String start_date;;
	private String end_date;
	private String coord_order;
	
	public String getTrip_member_count() {
		return trip_member_count;
	}
	public void setTrip_member_count(String trip_member_count) {
		this.trip_member_count = trip_member_count;
	}
	public WriteCoordDataBean getCoordList() {
		return coordList;
	}
	public void setCoordList(WriteCoordDataBean coordList) {
		this.coordList = coordList;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getCoord_order() {
		return coord_order;
	}
	public void setCoord_order(String coord_order) {
		this.coord_order = coord_order;
	}
}
