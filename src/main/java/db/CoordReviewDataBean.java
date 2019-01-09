package db;

import java.sql.Timestamp;

public class CoordReviewDataBean {
	public int coord_id;
	public String user_id;
	public String review_comment;
	public int review_point;
	private Timestamp user_review_reg_date;	
	
	//guess value, need this for viewPage
	private String coord_name;
	private CoordDataBean coordinate;			//varchar (20), not null
	
	public int getCoord_id() {
		return coord_id;
	}
	public void setCoord_id(int coord_id) {
		this.coord_id = coord_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getReview_comment() {
		return review_comment;
	}
	public void setReview_comment(String review_comment) {
		this.review_comment = review_comment;
	}
	public int getReview_point() {
		return review_point;
	}
	public void setReview_point(int review_point) {
		this.review_point = review_point;
	}
	public Timestamp getUser_review_reg_date() {
		return user_review_reg_date;
	}
	public void setUser_review_reg_date(Timestamp user_review_reg_date) {
		this.user_review_reg_date = user_review_reg_date;
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
	public void setCoordinate(int coord_id) {
		CoordDBBean coordDao=new CoordDBBean();
		setCoordinate(coordDao.getCoordinate(coord_id));
	}
}
