package db;

//Mapping table
//database table : pao_members
//please add pao_view_members(only for select)
public class MemberDataBean {
	//FK
	private String user_id;				//varchar (20), not null, on delete cascade
	private int trip_id;				//int (8), not null, on delete cascade
	//guest value from pao_user 
	public String user_name;			//varchar  (20), not null
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}	
}
