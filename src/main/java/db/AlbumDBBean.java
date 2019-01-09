package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class AlbumDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	public int addPhoto(AlbumDataBean albumDto) {
		return session.insert("album.addPhoto",albumDto);
	}
	public int delPhoto(int photo_id) {
		return session.delete("album.delPhoto",photo_id);
	}
	public List<AlbumDataBean> getAlbum(){
		return session.selectList("album.getAlbum");
	}
	public List<AlbumDataBean> getBoardAlbum(Map<String,Integer>map){
		return session.selectList("album.getBoardAlbum",map);
	}
	public int getAlbumCount() {
		return session.selectOne("album.getAlbumCount");
	}
//	쓰이는 위치가  없는 메서드
//	public int getPhotoCount() {
//		return session.selectOne("album.getPhotoCount");
//	}
//	public int getBoardCount(int board_no) {
//		return session.selectOne("album.getBoardPCount",board_no);
//	}
	public List<String> getPhoto_urls(int trip_id){
		return session.selectList("album.getPhoto_urls",trip_id);
	}
	
	
	public String getThumbnail (int board_no) {
		//get an url from view 'pao_thumbnail'
		return session.selectOne("album.getThumbnail", board_no);
	}
	////////////////////////////////NEW - talysa7////////////////////////////////////
	public List<AlbumDataBean> getAllPhotos() {
		return session.selectList("album.getAllPhotos");
	}
	public List<AlbumDataBean> getPhotosByTripId(int trip_id) {
		return session.selectList("album.getPhotosByTripId", trip_id);
	}
}
