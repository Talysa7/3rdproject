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
	public int getCount() {
		return session.selectOne("album.getPCount");
	}
	public int getBoardCount(int board_no) {
		return session.selectOne("album.getBoardPCount",board_no);
	}
	public List<String> getPhoto_urls(int board_no){
		return session.selectList("album.getPhoto_urls",board_no);
	}
	
	
	public String getThumbnail (int noard_no) {
		//get an url from view 'pao_thumbnail'
		return session.selectOne("album.getThumbail", noard_no);
	}
}
