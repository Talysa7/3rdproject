package Temp;

import java.sql.Date;
import java.util.List;

//database table : pao_board
public class BoardDataBean {
	private int board_no;					//int (10), PK
	private String board_title;				//varchar (50), not null
	private String board_content;			//text (3000), not null
	private int board_view_count;				//int (10), not null
	private int board_level;				//tinyint (1)
	private String board_contact;			//varchar (100)
	private Date board_reg_date;			//timestamp, not null
	//FK
	private String user_id;				//varchar (20), on delete cascade
	//guest value from database table : pao_user
	private String user_name;			//varchar (20)
										//Writer nickname of this article
										//If user_id is null, set it 'Ex-User'
	//guest value from database table : pao_album
	private String thumbnail;			//varchar (10)
										//Thumnail image of this article
										//If there is no thumbnail, set it to default image's url
	private List<String> board_tags;		//article's tag value list
	private List<TripDataBean> tripLists;	// 관련된 TripData들을 가져와 보관하기 위함.
	

	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_view_count() {
		return board_view_count;
	}
	public void setBoard_view_count(int board_view_count) {
		this.board_view_count = board_view_count;
	}
	public int getBoard_level() {
		return board_level;
	}
	public void setBoard_level(int board_level) {
		this.board_level = board_level;
	}
	public String getBoard_contact() {
		return board_contact;
	}
	public void setBoard_contact(String board_contact) {
		this.board_contact = board_contact;
	}
	public Date getBoard_reg_date() {
		return board_reg_date;
	}
	public void setBoard_reg_date(Date board_reg_date) {
		this.board_reg_date = board_reg_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_view_count() {
		return board_view_count;
	}
	public void setBoard_view_count(int board_view_count) {
		this.board_view_count = board_view_count;
	}
	public int getBoard_level() {
		return board_level;
	}
	public void setBoard_level(int board_level) {
		this.board_level = board_level;
	}
	public String getBoard_contact() {
		return board_contact;
	}
	public void setBoard_contact(String board_contact) {
		this.board_contact = board_contact;
	}
	public Date getBoard_reg_date() {
		return board_reg_date;
	}
	public void setBoard_reg_date(Date board_reg_date) {
		this.board_reg_date = board_reg_date;
	}
	public List<String> getBoard_tags() {
		return board_tags;
	}
	public void setBoard_tags(List<String> board_tags) {
		this.board_tags = board_tags;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public List<TripDataBean> getTripLists() {
		return tripLists;
	}
	public void setTripLists(List<TripDataBean> tripLists) {
		this.tripLists = tripLists;
	}
	
}
