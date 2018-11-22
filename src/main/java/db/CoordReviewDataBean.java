package db;

public class CoordReviewDataBean {
	public int coord_id;
	public String user_id;
	public String review_comment;
	public int review_point;
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
	
}
