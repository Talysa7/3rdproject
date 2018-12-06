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
			System.out.println(finalJson.toString());
			br.write(finalJson.toString() + "\n");
			br.flush();
			
			jsonPosts.add(finalJson);
		}
		br.close();
		return jsonPosts;
	}
	
	public JSONArray makeMemberLog() throws ParseException, IOException {
		BufferedWriter br = Files.newBufferedWriter(Paths.get("C:/Users/Playdata/desktop/csv/boardLog.csv"), Charset.forName("UTF-8"));
		ObjectMapper mapper = new ObjectMapper(); 
		JSONArray members = new JSONArray();
		
		return members;
	}
	
	public JSONArray makeCommentLog() throws ParseException, IOException {
		BufferedWriter br = Files.newBufferedWriter(Paths.get("C:/Users/Playdata/desktop/csv/commentLog.csv"), Charset.forName("UTF-8"));
		ObjectMapper mapper = new ObjectMapper();
		JSONArray jsonPosts = new JSONArray();
		
		for (int i=1; i<5001; i++) {
			List<CmtDataBean> comments = session.selectList("board.getComment", i);
			String templog = "";
			JSONObject finalJson = new JSONObject();
			
			templog = mapper.writeValueAsString(comments);
			JSONParser parser = new JSONParser();
			Object parseobj = parser.parse(templog);
			JSONObject postJson = (JSONObject) parseobj;		
			
			finalJson.put("result", postJson);
			finalJson.put("log_type", 4);
			System.out.println(finalJson.toString());
			br.write(finalJson.toString() + "\n");
			br.flush();
			
			jsonPosts.add(finalJson);
		}
		br.close();
		return jsonPosts;
	}
}
