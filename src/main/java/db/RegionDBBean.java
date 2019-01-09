package db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class RegionDBBean {
	private SqlSession session=SqlMapClient.getSession();

	//get region_types of coordinate from view 'pao_region_match'
	public List<String> getRegions(int coord_id) {
		return session.selectList("location.getRegions", coord_id);
	}
}
