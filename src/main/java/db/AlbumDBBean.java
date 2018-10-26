package db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class AlbumDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public int addPhoto(AlbumDataBean albumDto) {
		return session.insert("db.addPhoto",albumDto);
	}
	public int delPhoto(int photo_id) {
		return session.delete("db.delPhoto",photo_id);
	}
	public void addPhotos() {
	}
	public void delPhotos() {
	}
	public List<AlbumDataBean> getAlbum(){
		return session.selectList("db.getAlbum");
	}
	public List<AlbumDataBean> getBoardAlbum(Map<String,Integer>map){
		return session.selectList("db.getBoardAlbum",map);
	}
	public int getCount() {
		return session.selectOne("db.getPCount");
	}
	public int getBoardCount(int tb_no) {
		return session.selectOne("db.getBoardPCount",tb_no);
	}
	public List<String> getPhoto_urls(int tb_no){
		return session.selectList("db.getPhoto_urls",tb_no);
	}
}