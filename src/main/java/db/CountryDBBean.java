package db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class CountryDBBean {

	private SqlSession session=SqlMapClient.getSession();
	
	public List<CountryDataBean> selectCountry(int board_no) {
		return session.selectList("location.selectCountry",board_no);
	}
	public String getCountryName(int coord_id) {
		return session.selectOne("location.getCountryName", coord_id);		
	}
	
}
