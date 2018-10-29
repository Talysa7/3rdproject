package Temp;

import java.sql.Date;
import java.util.List;

//database table : pao_board
public class BoardDataBean {
	private int b_no;					//int (10), PK
	private String b_title;				//varchar (50), not null
	private String b_content;			//text (3000), not null
	private int b_v_count;				//int (10), not null
	private int b_level;				//tinyint (1)
	private String b_contact;			//varchar (100)
	private Date b_reg_date;			//timestamp, not null
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
	private List<String> b_tags;		//article's tag value list
	
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public int getB_v_count() {
		return b_v_count;
	}
	public void setB_v_count(int b_v_count) {
		this.b_v_count = b_v_count;
	}
	public int getB_level() {
		return b_level;
	}
	public void setB_level(int b_level) {
		this.b_level = b_level;
	}
	public String getB_contact() {
		return b_contact;
	}
	public void setB_contact(String b_contact) {
		this.b_contact = b_contact;
	}
	public Date getB_reg_date() {
		return b_reg_date;
	}
	public void setB_reg_date(Date b_reg_date) {
		this.b_reg_date = b_reg_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String setUser_name(String user_id) {
		//This is a guest value, we can get it from pao_user
		if (user_id!=null) {
			UserDBBean userDao=new UserDBBean();
			this.user_name=userDao.getUser_name(user_id);
		} else {
			this.user_name="Ex-User";
		}
		return user_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public String setThumbnail(int b_no) {
		AlbumDBBean albumDao=new AlbumDBBean();
		thumbnail=albumDao.getThumbnail(b_no);
		if (thumbnail==null) {
			thumbnail="";	//put the url of default image
		}
		return thumbnail;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setB_tags(int b_no) {
		TagDBBean tagDao=new TagDBBean();
		this.b_tags=tagDao.getB_tags(b_no);
	}
	public List<String> getB_tags() {
		return b_tags;
	}
}
