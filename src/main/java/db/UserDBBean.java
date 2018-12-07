package db;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.SqlMapClient;


public class UserDBBean {
	private SqlSession session=SqlMapClient.getSession();
	
		public String getUser_name(String user_id) {
			return session.selectOne("user.getUserName", user_id);
		}
		
		public UserDataBean getUser( String user_id ) {
			return session.selectOne( "user.getUser", user_id );
		}
		
		public String getUserName(String user_id) { 
			return session.selectOne("user.getUserName", user_id); 
		} 
		
		public int nameCheck( String user_name ) {
			return session.selectOne( "user.nameCheck", user_name);
		}
		
		public int checkId( String user_id ) {
			return session.selectOne( "user.checkId", user_id);
		}
		
		public List<UserDataBean> getUsers(Map<String, Integer> map) {
			return session.selectList("user.getUsers",map);
		}

		public int getAllUserCount() {
			return session.selectOne("user.getAllUserCount");
		}


		public int insertUser_tag(Map<String, String> map) {
			return session.update("tag.insertUser_tag", map);		
		}
		

		public int insertUser( UserDataBean UserDto ) {
			return session.insert("user.insertUser", UserDto);
		}
		
		
		public UserDataBean getUserEmailId(String email) { 
			return session.selectOne("user.getUserEmailId", email); 
		} 
		
		public UserDataBean getUserEmailPasswd(String email) { 
			return session.selectOne("user.getUserEmailPasswd", email); 
		} 
		
		public int EmailCheck( String email ) {
			return session.selectOne( "user.emailCheck", email);
		}
		public int deleteUser( String user_id ) {
			return session.delete("user.deleteUser", user_id);
		}
		public int modifyUser( UserDataBean UserDto ) {
			return session.update( "user.modifyUser", UserDto );
		}
		public int getUserLevel(String user_id) {
			return session.selectOne("user.getUserLevel",user_id);
		}
		
		public String getUserId(String user_name) { 
			return session.selectOne("user.getUserId", user_name); 
		} 
//		send jsonarray
		public void makeLoginLog(UserDataBean userDto, int result, int userType, String id, String passwd) {
			JSONObject wrapObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			
			JSONObject jsonObject = new JSONObject();
			if(userDto != null) {
				jsonObject.put("user_id", userDto.getUser_id());
				jsonObject.put("passwd", userDto.getPasswd());
				jsonObject.put("reg_date", userDto.getReg_date());
				jsonObject.put("user_age", userDto.getUser_age());
				jsonObject.put("user_level", userType);
				jsonObject.put("user_name", userDto.getUser_name().toString());
				jsonObject.put("email", userDto.getEmail().toString());
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
			jsonjson.put("login_time", new Timestamp(System.currentTimeMillis()));
			jsonArray.add(jsonjson);
			wrapObject.put("result",jsonArray);
			wrapObject.put("log_type", 6);
			//확인용
			System.out.println(wrapObject);
		}
		
		public void modUserLog(UserDataBean beforeinfo, UserDataBean userDto, List<TagDataBean> list, List<TagDataBean> userTags) throws ClassCastException, ParseException, IOException{
			JSONObject wrapObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject Obj = new JSONObject();
			JSONObject jsonjson = new JSONObject();
			JSONObject jsonObj = new JSONObject();
			JSONObject jsonObject = new JSONObject();
			ObjectMapper mapper = new ObjectMapper(); 
			if(userTags != beforeinfo) { //태그 안 바뀐 경우
				for(int i=0; i<list.size(); i++) {
					String templog = "";
					beforeinfo.setUser_tags(list);
					templog = mapper.writeValueAsString(beforeinfo);
					JSONParser parser = new JSONParser();
					Object parseobj = parser.parse(templog);
					JSONObject userJson = (JSONObject) parseobj;
					jsonObj.put("before_data", userJson);
				}
				for(int j=0; j<userTags.size(); j++) {
					String templog = "";
					userDto.setUser_tags(userTags);
					templog = mapper.writeValueAsString(userDto);
					JSONParser parser = new JSONParser();
					Object parseobj = parser.parse(templog);
					JSONObject userJson = (JSONObject) parseobj;
					jsonObject.put("after_data", userJson);
				}
			}else {
				for(int i=0; i<list.size(); i++) {
					String templog = "";
					beforeinfo.setUser_tags(list);
					templog = mapper.writeValueAsString(beforeinfo);
					JSONParser parser = new JSONParser();
					Object parseobj = parser.parse(templog);
					JSONObject userJson = (JSONObject) parseobj;
					jsonObj.put("before_data", userJson);
				}
				jsonjson.put("user_id", userDto.getUser_id());
				jsonjson.put("passwd", userDto.getPasswd());
				jsonjson.put("user_name", userDto.getUser_name().toString());
				jsonObject.put("after_data", jsonjson);
			}			
			JSONObject Object = new JSONObject();	
			jsonArray.add(jsonObj);
			jsonArray.add(jsonObject);
			wrapObject.put("result",jsonArray);
			wrapObject.put("log_type", 7);
			//확인용
			System.out.println(wrapObject);
		}
}

