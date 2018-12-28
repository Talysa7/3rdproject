package db;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.SqlMapClient;

public class LogDBBean {
	@Resource
	private BoardDBBean boardDao;
	@Resource
	private MemberDBBean memberDao;
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
			postJson.remove("thumbnail");
			postJson.remove("board_view_count");
			JSONArray jaboardtags = (JSONArray) postJson.get("board_tags");
			postJson.remove("board_tags");
			JSONArray reboardtags = new JSONArray();
			for (int j = 0; j<jaboardtags.size(); j++) {
				JSONObject jaboardtag = (JSONObject) jaboardtags.get(j);
				jaboardtag.remove("board_id");
				jaboardtag.remove("user_id");
				reboardtags.add(jaboardtag);
			}
			postJson.put("board_tags",reboardtags);
			
			
			List<TagDataBean> usertags = session.selectList("tag.getUserTags", postBean.getUser_id());
			templog = mapper.writeValueAsString(usertags);
			parseobj = parser.parse(templog);
			JSONArray usertagJson = (JSONArray) parseobj;
			JSONArray reusertags = new JSONArray();
			for (int j=0; j<usertagJson.size(); j++) {
				JSONObject jausertag = (JSONObject) usertagJson.get(j);
				jausertag.remove("coordinate");
				jausertag.remove("board_no");
				jausertag.remove("trip_members");
				jausertag.remove("trip_member_count");
				reusertags.add(jausertag);
			}
			postJson.put("user_tags", reusertags);					// boardtag 추가
			finalJson.put("result", postJson);
			finalJson.put("log_type", 3);
			br.write( finalJson.toString()+ "\r\n");
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
			tripJson.remove("coordinate");
			tripJson.remove("board_no");
			tripJson.remove("trip_members");
			tripJson.remove("trip_member_count");
			
			UserDataBean user = session.selectOne("user.getUser", memInfo.getUser_id());		
			List<TagDataBean> usertags = session.selectList("tag.getUserTags", memInfo.getUser_id());
			user.setUser_tags(usertags);

			templog = mapper.writeValueAsString(user);
			parser = new JSONParser();
			parseobj = parser.parse(templog);
			JSONObject userJson = (JSONObject) parseobj;		//	유저정보 제이슨 처리.
			JSONArray jauserTags = (JSONArray) userJson.get("user_tags");
			userJson.remove("user_tags");
			
			JSONArray reuserTags = new JSONArray();
			for (int j=0; j<jauserTags.size(); j++) {
				JSONObject jauserTag = (JSONObject) jauserTags.get(i);
				jauserTag.remove("user_id");
				jauserTag.remove("board_id");
				reuserTags.add(jauserTag);
			}
			userJson.put("user_tags",reuserTags) ;
			
			tempJson.put("trip_data", tripJson);
			tempJson.put("user_data", userJson);
			
			finalJson.put("log_type", 8);
			finalJson.put("result", tempJson);
			
			br.write(finalJson.toString() + "\r\n");
			br.flush();
				
			members.add(finalJson);
		}
		br.close();
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
//	send jsonarray
	public void makeLoginLog(UserDataBean userDto, int result, int userType, String id, String passwd) {
		JSONObject wrapObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		JSONObject jsonObject = new JSONObject();
		if(userDto != null) {
			jsonObject.put("user_id", userDto.getUser_id());
			jsonObject.put("passwd", userDto.getPasswd());
			jsonObject.put("reg_date", userDto.getReg_date().getTime());
			jsonObject.put("user_age", userDto.getUser_age());
			jsonObject.put("user_level", userType);
			jsonObject.put("user_name", userDto.getUser_name());
			jsonObject.put("email", userDto.getEmail());
			jsonObject.put("gender", userDto.getGender());
		}else {
			jsonObject.put("user_id",id);
			jsonObject.put("passwd", passwd);
		}			
		JSONObject Object = new JSONObject();
		Object.put("user_data", jsonObject);
		jsonArray.add(Object);
		JSONObject jsonjson = new JSONObject();
		jsonjson.put("login_success", result);
		jsonjson.put("login_time", new Timestamp(System.currentTimeMillis()).getTime());
		jsonArray.add(jsonjson);
		wrapObject.put("result",jsonArray);
		wrapObject.put("log_type", 6);
		//확인용
		System.out.println(wrapObject);
		Map<String, Object> map = new HashMap<String, Object>(	);
		map.put("log_str", wrapObject.toJSONString());
		session.insert("board.insertLog",map);
	}
	
	public void modUserLog(UserDataBean beforeinfo, UserDataBean userDto, List<TagDataBean> list, List<TagDataBean> userTags) throws ClassCastException, ParseException, IOException{
		JSONObject wrapObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject Obj = new JSONObject();
		JSONObject jsonjson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			if(list.size()==0) {
				Obj.put("user_id", beforeinfo.getUser_id());
				Obj.put("passwd", beforeinfo.getPasswd());
				Obj.put("user_name", beforeinfo.getUser_name());
				Obj.put("user_tags", "tag 없음");
				jsonObj.put("before_data", Obj);
			}else {
				for(int i=0; i<list.size(); i++) {
					String templog = "";
					beforeinfo.setUser_tags(list);
					templog = mapper.writeValueAsString(beforeinfo);
					JSONParser parser = new JSONParser();
					Object parseobj = parser.parse(templog);
					JSONObject userJson = (JSONObject) parseobj;
					userJson.put("user_id", beforeinfo.getUser_id());
					userJson.remove("board_id");					
					userJson.remove("gender");
					userJson.remove("user_level");
					userJson.remove("user_age");
					userJson.remove("email");
					userJson.remove("reg_date");
					jsonObj.put("before_data", userJson);
				}
			}
		}catch(NullPointerException e) {
			Obj.put("user_id", beforeinfo.getUser_id());
			Obj.put("passwd", beforeinfo.getPasswd());
			Obj.put("user_name", beforeinfo.getUser_name());
			Obj.put("user_tags", "tag 없음");
			jsonObj.put("before_data", Obj);
		}
			if(userTags.size() == 0) {
				jsonjson.put("user_id", userDto.getUser_id());
				jsonjson.put("passwd", userDto.getPasswd());
				jsonjson.put("user_name", userDto.getUser_name());
				jsonjson.put("user_tags", "tag없음");
				jsonjson.put("reg_date", new Timestamp(System.currentTimeMillis()).getTime());
				jsonObject.put("after_data", jsonjson);
			}else {
				for(int j=0; j<userTags.size(); j++) {
					String templog = "";
					String user_id = userDto.getUser_id();
					userDto.setUser_tags(userTags);
					userDto.getUser_tags().get(j).setUser_id(user_id);
					templog = mapper.writeValueAsString(userDto);
					JSONParser parser = new JSONParser();
					Object parseobj = parser.parse(templog);
					JSONObject userJson = (JSONObject) parseobj;
					userJson.put("user_id", user_id);
					userJson.remove("board_id");					
					userJson.remove("gender");
					userJson.remove("user_level");
					userJson.remove("user_age");
					userJson.remove("email");
					userJson.put("reg_date",new Timestamp(System.currentTimeMillis()).getTime());
					jsonObject.put("after_data", userJson);
					
				}
			}
			JSONObject Object = new JSONObject();	
			jsonArray.add(jsonObj);
			jsonArray.add(jsonObject);
			wrapObject.put("result",jsonArray);
			wrapObject.put("log_type", 7);
			//확인용
			System.out.println(wrapObject);
			Map<String, Object> map = new HashMap<String, Object>(	);
			map.put("log_str", wrapObject.toJSONString());
			session.insert("board.insertLog",map);
		}
//	댓글 등록
	public void insertCommentLog(CmtDataBean cmtDto) {
		JSONObject wrapObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("comment_id", cmtDto.getComment_id());
		jsonObject.put("comment_content", cmtDto.getComment_content());
		jsonObject.put("comment_reg_date", new Timestamp(System.currentTimeMillis()).getTime());
		jsonObject.put("board_no", cmtDto.getBoard_no());
		jsonObject.put("user_id", cmtDto.getUser_id());
		
		String user_id = cmtDto.getUser_id();
		String user_name;
		if (user_id == null || user_id.equals("")) {
			user_name = "Ex-User";
			cmtDto.setUser_name(user_name);
		} else {
			user_name = memberDao.getUserName(user_id);
			cmtDto.setUser_name(user_name);
		}
		jsonObject.put("user_name", cmtDto.getUser_name());
		jsonArray.add(jsonObject);
		wrapObject.put("result",jsonArray);
		wrapObject.put("log_type", 4);
		//FIXME : 확인 필요
		System.out.println(wrapObject);
		Map<String, Object> map = new HashMap<String, Object>(	);
		map.put("log_str", wrapObject.toJSONString());
		session.insert("board.insertLog",map);
	}
//평판 등록
	public void insertPersonReviewLog(ReviewDataBean reviewDto) {
		JSONObject wrapObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("user_id", reviewDto.getUser_id());
		jsonObject.put("reviewer_id", reviewDto.getReviewer_id());
		jsonObject.put("trip_id", reviewDto.getTrip_id());
		jsonObject.put("review_comment", reviewDto.getReview_comment());
		jsonObject.put("review_point", reviewDto.getReview_point());
		jsonObject.put("user_review_reg_date", reviewDto.getUser_review_reg_date().getTime());
		jsonArray.add(jsonObject);
		
		wrapObject.put("result",jsonArray);
		wrapObject.put("log_type", 5);
		System.out.println(wrapObject);
		Map<String, Object> map = new HashMap<String, Object>(	);
		map.put("log_str", wrapObject.toJSONString());
		session.insert("board.insertLog",map);
	}
//검색 로그	
	public void searchTripLog(String selectedType, String keyword ) throws ClassCastException, JsonProcessingException, ParseException {
		JSONObject wrapObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject Obj = new JSONObject();
		JSONObject jsonjson = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper(); 
		List<BoardDataBean> foundList;
		//find trips for each type
		if(selectedType.equals("schedule")) {
			foundList=boardDao.findPostByKeyword(keyword);
		} else {
			foundList=boardDao.findPostByUser(keyword);
		}
		
		for (int i=0; i<foundList.size(); i++) {
			BoardDataBean postBean = foundList.get(i);
			List<TagDataBean> boardtags = session.selectList("tag.getPostTags", foundList.get(i).getBoard_no());
			List<TripDataBean> trips = session.selectList("board.getTripList", foundList.get(i).getBoard_no());
			postBean.setBoard_tags(boardtags);
			postBean.setTripLists(trips);
			
			JSONObject finalJson = new JSONObject();
			String templog = "";
			templog = mapper.writeValueAsString(postBean);
			JSONParser parser = new JSONParser();
			Object parseobj = parser.parse(templog);
			JSONObject object = (JSONObject) parseobj;		//	board까지 json처리 완료
			jsonArray.add(object);
		}
		jsonObj.put("search_data", jsonArray);	
		jsonObj.put("count", foundList.size());
		jsonObj.put("keyword", keyword);
		if(selectedType.equals("schedule")) {
			jsonObj.put("selectedType", "게시물");
		}else {
			jsonObj.put("selectedType", "작성자");
		}		
		wrapObject.put("result",jsonObj);
		wrapObject.put("log_type", 1);
		//확인용
		System.out.println(wrapObject);
		
		Map<String, Object> map = new HashMap<String, Object>(	);
		map.put("log_str", wrapObject.toJSONString());
		session.insert("board.insertLog",map);
	}
		
	public int insertLog(JSONObject wrapObject) {
		Map<String, Object> map = new HashMap<String, Object>(	);
		map.put("log_str", wrapObject.toJSONString());		
		return session.insert("board.insertLog",map);
	}	
	
	public void makeAdvanceSearchLog(BoardDataBean boardDto) {
		JSONObject wrapObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("fromDate", boardDto.getFromDate());
		jsonObject.put("toDate", boardDto.getToDate());
		jsonObject.put("searchPeriod", boardDto.getSearchPeriod());
		jsonObject.put("searchTag", boardDto.getSearchTag());
		jsonObject.put("searchSite", boardDto.getSearchSite());
				
		JSONObject Object = new JSONObject();
		Object.put("advanceSearch_data", jsonObject);
		jsonArray.add(Object);
		JSONObject jsonjson = new JSONObject();
		jsonArray.add(jsonjson);
		wrapObject.put("result",jsonArray);
		wrapObject.put("log_type", 2);
		//확인용
		System.out.println(wrapObject);
		Map<String, Object> map = new HashMap<String, Object>(	);
		map.put("log_str", wrapObject.toJSONString());
		session.insert("board.insertLog",map);
	}
	
}
