package db;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//database view(with user_name) : pao_view_trip
//database table : pao_trip
public class TripDataBean {
	private int trip_id;										//int (10), PK, not null, on delete cascade
	private int trip_member_count;					//smallint (4), not null
	private Date start_date;								//timestamp, not null
	private Date end_date;								//timestamp, not null
	private int coord_order;								// tinyint (1) 
	//FK
	private int board_no;									//int (10), not null
	private int coord_id;									//int (8), not null
	//guest value from pao_view_members
	//We should set it once by trip_id when we need it.
	private List<MemberDataBean> trip_members;			//varchar (20), not null
	//guest value from pao_view_trip
	//Be careful, when we get trip list from pao_trip, then we can't get this value!
	private String coord_name;
	//guest value from pao_coordinate
	//We should set it once by trip_id when we need it.
	private CoordDataBean coordinate;			//varchar (20), not null
	//we need below value to get unreviewed members
	private List<MemberDataBean> review_members;
	//One photo from its coordinate
	private String thumbnail;
	
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
	public List<MemberDataBean> getTrip_members() {
		return trip_members;
	}
	//for convenience
	//by talysa7
	public List<MemberDataBean> getTrip_members(int trip_id) {
		this.setTrip_members(trip_id);
		return trip_members;
	}
	public void setTrip_members(List<MemberDataBean> trip_members) {
		this.trip_members = trip_members;
	}
	//get a list of each trip member's user_name from pao_view_members
	//by talysa7
	public void setTrip_members(int trip_id) {
		MemberDBBean memberDao=new MemberDBBean();
		this.trip_members=memberDao.getMembers(trip_id);
	}
	public String getCoord_name() {
		return coord_name;
	}
	public void setCoord_name(String coord_name) {
		this.coord_name = coord_name;
	}
	public CoordDataBean getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(CoordDataBean coordinate) {
		this.coordinate = coordinate;
	}
	//get coordinate of this trip
	public void setCoordinate(int trip_id) {
		CoordDBBean coordDao=new CoordDBBean();
		setCoordinate(coordDao.getCoordinate(trip_id));
	}
	public int getCoord_order() {
		return coord_order;
	}
	public void setCoord_order(int coord_order) {
		this.coord_order = coord_order;
	}
	public List<MemberDataBean> getReview_members() {
		return review_members;
	}
	public void setReview_members(List<MemberDataBean> review_members) {
		this.review_members = review_members;
	}
	public List<MemberDataBean> getReview_members(int trip_id) {
		this.setReview_members(trip_id);
		return review_members;
	}
	public void setReview_members(int trip_id) {
		ReviewDBBean reviewDao = new ReviewDBBean();
		this.review_members=reviewDao.getReviewMembers(trip_id);
	}
	public List<MemberDataBean> getReview_members(Map<String, Object> user) {
		this.setReview_members(user);
		return review_members;
	}
	public void setReview_members(Map<String, Object> user) {
		ReviewDBBean reviewDao = new ReviewDBBean();
		this.review_members=reviewDao.getReview(user);
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
