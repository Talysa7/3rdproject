package Temp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import Temp.BoardDataBean;

public class CountryDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
	public List<BoardDataBean> selectCountry(int board_no) {
		return session.selectList("location.selectCountry",b_no);
	}
}
