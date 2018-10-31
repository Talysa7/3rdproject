package db;

//Mapping view
//database view : pao_members
public class MemberDataBean {
	//FK
	private String user_id;				//varchar (20), not null, on delete cascade
	private int trip_id;				//int (8), not null, on delete cascade
	
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
}
