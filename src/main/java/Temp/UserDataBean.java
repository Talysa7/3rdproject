package Temp;

import java.sql.Date;
import java.util.List;

//database table : pao_user
public class UserDataBean {
	private String user_id;			//varchar (20), PK
	private String passwd;			//varchar (20), not null
	private String user_name;		//varchar (20), UK, not null
	private String email;			//varchar (30), UK, not null
	private Date reg_date;			//timestamp, not null
	private int gender;				//smallint (1)
	private int user_level;			//smallint (1)
	private int user_age;			//smallint (3)
	private List<String> user_tags;	//user's tag value list
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getUser_level() {
		return user_level;
	}
	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}
	public int getUser_age() {
		return user_age;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	public void setUser_tags(String user_id) {
		TagDBBean tagDao=new TagDBBean();
		this.user_tags=tagDao.getUser_tags(user_id);
	}
	public List<String> getUser_tags(String user_id) {
		return user_tags;
	}
}
