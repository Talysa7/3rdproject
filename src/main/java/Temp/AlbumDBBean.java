package Temp;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class AlbumDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	public String getThumbnail (int b_no) {
		//get an url from view 'pao_thumbnail'
		return session.selectOne("album.getThumbail", b_no);
	}
}
