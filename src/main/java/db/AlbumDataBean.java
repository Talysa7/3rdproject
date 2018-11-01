package db;

//database table : pao_album
public class AlbumDataBean {
	private int photo_id;			//int (10), PK
	private String photo_url;		//varchar (200), not null
	//FK
	private String user_id;			//varchar (20), on delete cascade
	private int board_no;				//int (10), not null, on delete cascade
	
	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
}
