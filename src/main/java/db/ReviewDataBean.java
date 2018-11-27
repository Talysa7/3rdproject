package db;

import java.util.List;

public class ReviewDataBean {
	private String user_id;
	private String reviewer_id;
	private int review_point;
	private String review_comment;
	private int trip_id;
	
	private List<ReviewDataBean> reviewList;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getReviewer_id() {
		return reviewer_id;
	}
	public void setReviewer_id(String reviewer_id) {
		this.reviewer_id = reviewer_id;
	}
	public int getReview_point() {
		return review_point;
	}
	public void setReview_point(int review_point) {
		this.review_point = review_point;
	}
	public String getReview_comment() {
		return review_comment;
	}
	public void setReview_comment(String review_comment) {
		this.review_comment = review_comment;
	}
	public int getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}
	public List<ReviewDataBean> getReviewList() {
		return reviewList;
	}
	public void setReviewList(List<ReviewDataBean> reviewList) {
		this.reviewList = reviewList;
	}
	
	
	
}
