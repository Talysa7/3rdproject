package db;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.SqlMapClient;

public class LogDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	public JSONArray makeBoardLog() throws ParseException, IOException {
		BufferedWriter br = Files.newBufferedWriter(Paths.get("C:/Users/Playdata/desktop/csv/boardLog.csv"), Charset.forName("UTF-8"));
		ObjectMapper mapper = new ObjectMapper(); 
		List<BoardDataBean> postList = session.selectList("board.tempSelect");	// 로그데이터 생성을 위한 임시용 쿼리. 추후 삭제 예정.
		JSONArray jsonPosts = new JSONArray();
		
		for (int i=1; i<5001; i++) {
			BoardDataBean postBean = postList.get(i-1);
			List<TagDataBean> boardtags = session.selectList("tag.getPostTags", i);
			List<TripDataBean> trips = session.selectList("board.getTripList", i);
			postBean.setBoard_tags(boardtags);
			postBean.setTripLists(trips);
			String templog = "";
			JSONObject finalJson = new JSONObject();
			
			templog = mapper.writeValueAsString(postBean);
			JSONParser parser = new JSONParser();
			Object parseobj = parser.parse(templog);
			JSONObject postJson = (JSONObject) parseobj;		//	board까지 json처리 완료
			
			
			List<TagDataBean> usertags = session.selectList("tag.getUserTags", postBean.getUser_id());
			templog = mapper.writeValueAsString(usertags);
			parseobj = parser.parse(templog);
			JSONArray usertagJson = (JSONArray) parseobj;
			postJson.put("user_tags", usertagJson);					// boardtag 추가
			
			finalJson.put("result", postJson);
			finalJson.put("log_type", 3);
			br.write("%%" + finalJson.toString() + "%%" + "\r\n");
			br.flush();
			
			jsonPosts.add(finalJson);
		}
		br.close();
		return jsonPosts;
	}
	
	public JSONArray makeMemberLog() throws ParseException, IOException {
		BufferedWriter br = Files.newBufferedWriter(Paths.get("C:/Users/Playdata/desktop/csv/tripJoinLog.csv"), Charset.forName("UTF-8"));
		ObjectMapper mapper = new ObjectMapper(); 
		JSONArray members = new JSONArray();
		List <MemberDataBean> memList = session.selectList("user.getMem");
		
		for (int i=0; i<memList.size(); i++) {
			String templog = "";
			JSONObject finalJson = new JSONObject();
			JSONObject tempJson = new JSONObject();
			MemberDataBean memInfo = memList.get(i);
			
			TripDataBean trip = session.selectOne("board.getTrip",memInfo.getTrip_id());
			
			templog = mapper.writeValueAsString(trip);
			JSONParser parser = new JSONParser();
			Object parseobj = parser.parse(templog);
			JSONObject tripJson = (JSONObject) parseobj;		//	트립정보 제이슨 처리.
			
			UserDataBean user = session.selectOne("user.getUser", memInfo.getUser_id());		
			List<TagDataBean> usertags = session.selectList("tag.getUserTags", memInfo.getUser_id());
			user.setUser_tags(usertags);

			templog = mapper.writeValueAsString(user);
			parser = new JSONParser();
			parseobj = parser.parse(templog);
			JSONObject userJson = (JSONObject) parseobj;		//	유저정보 제이슨 처리.
			
			tempJson.put("trip_data", tripJson);
			tempJson.put("user_data", userJson);
			
			finalJson.put("log_type", 8);
			finalJson.put("result", tempJson);
			
			br.write("%%"+finalJson.toString() + "%%" + "\r\n");
			br.flush();
				
			members.add(finalJson);
		}
		br.close();
		return members;
	}
}
