package db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import db.TagDataBean;

public class TagDBBean {
		private SqlSession session=SqlMapClient.getSession();
	
		
		public int insertUser_tag(Map<String, String> map) {
			return session.update("tag.insertUser_tag", map);		
		}
		
		public int insertTag(String tag_value) {
			return session.insert("tag.insertTag",tag_value);
		}
		
		public int insertCityTag(String tag_value) {
			return session.insert("tag.insertCityTag",tag_value);
		}
		//tag list
		public  List<TagDataBean> getTags(Map<String, Integer> map){
			return session.selectList("tag.getTags",map);	
		}
		//total tag
		public int getTagCount() {
			return session.selectOne("tag.getTagCount");
		}
		//check tag
		public int checkTag(String tag_value) {
			return session.selectOne("tag.checkTag",tag_value);
		}
		//show tag info
		public TagDataBean getTag(int tag_id) {
			return session.selectOne("tag.getTag",tag_id);
		}
		//modify tag
		public int modifyTag(TagDataBean tagDao) {
			return session.update("tag.modifyTag",tagDao);
		}
		//delete tag
		public int deleteTag(String tag_id) {
			return session.delete("tag.deleteTag",tag_id);
		}
		public List<TagDataBean> getUserTags(String user_id) {
			return session.selectList("tag.getUserTags", user_id);
		}
		public List<TagDataBean> getPostTags(int board_no) {
			return session.selectList("tag.getPostTags", board_no);	// need to check after making boardDB.xml
		}
		public List<TagDataBean> getStyleTags() {
			return session.selectList("tag.getStyleTags");
		}
		public List<TagDataBean> getCityTags() {
			return session.selectList("tag.getCityTags");
		}
		public List<TagDataBean> getCountryTags() {
			return session.selectList("tag.getCountryTags");
		}
		public int getTagId(String tag_value) {
			return session.selectOne("tag.getTagId", tag_value);
		}
		public int updateTripTags(int board_no, List<TagDataBean> tripTags) {
			int result=1;
			//set old Trip Tags
			List<TagDataBean> oldTripTags=getPostTags(board_no);
			//setter for query
			Map<String, Integer> tagSetter;
			//tester to check whether trip already has that tag
			boolean hasTag=false;
			//Check if the trip had these tags 
			for(TagDataBean tagDto:tripTags) {
				hasTag=false;
				for(TagDataBean otb:oldTripTags) {
					//if user didn't have such like a tag
					if(otb.getTag_id()==(tagDto.getTag_id())) {
						hasTag=true;
					}
				}
				//if user didn't have this tag, insert it!
				if(!hasTag) {
					tagSetter=new HashMap<String, Integer>();
					tagSetter.put("board_no", board_no);
					int tagId= tagDto.getTag_id();
					tagSetter.put("tag_id", tagId);
					result=session.update("tag.updateTripTags", tagSetter);
				}
			}
			return result;
		}
		
		public int updateUserTags(String user_id, List<TagDataBean> userTags) {
			int result=1;
			//set old User Tags
			List<TagDataBean> oldUserTags=getUserTags(user_id);
			//setter for query
			Map<String, String> tagSetter;
			//tester to check whether user already has that tag
			boolean hasTag=false;
			//Did user have this tag?
			try {
				for(TagDataBean tagDto:userTags) {
					hasTag=false;
					for(TagDataBean otb:oldUserTags) {
						//if user didn't have such like a tag
						if(otb.getTag_id()==(tagDto.getTag_id())) {
							hasTag=true;
						}
					}
					//if user didn't have this tag, insert it!
					if(!hasTag) {
						tagSetter=new HashMap<String, String>();
						tagSetter.put("user_id", user_id);
						tagSetter.put("tag_id", String.valueOf(tagDto.getTag_id()));
						result=session.update("tag.updateUserTag", tagSetter);
					}
				}
			}catch(NullPointerException e) {
				for(TagDataBean otb:oldUserTags) {
				tagSetter=new HashMap<String, String>();
				tagSetter.put("user_id", user_id);
				tagSetter.put("tag_id", String.valueOf(otb.getTag_id()));
				result=session.delete("tag.deleteUserTag", tagSetter);
				}
			}
			//user had that tag, but not anymore! 
			for(TagDataBean otb:oldUserTags) {
				hasTag=false;
				for(TagDataBean tb:userTags) {
					if(otb.getTag_id()==(tb.getTag_id())) { //여기서 tb는 board를 말하는지 위에 선언한 tb인지
						hasTag=true;
					}
				}
				//there is no such tag what was in old tag list! So delete it!
				if(!hasTag) {
					tagSetter=new HashMap<String, String>();
					tagSetter.put("user_id", user_id);
					tagSetter.put("tag_id", String.valueOf(otb.getTag_id()));
					result=session.delete("tag.deleteUserTag", tagSetter);
				}
			}
			
			return result;
		}
		public String getTagValue(int tag_id) {
			return session.selectOne("tag.getTagValue", tag_id);
		}
		
		public int setTripTag(Map<String, Integer> tagSetter) {
			return session.update("tag.insertTripTags", tagSetter);
		}	
		
		public List<TagDataBean> tagAutoComplete(String tag_value){
			return session.selectList("tag.tagAutoComplete", tag_value);
		}
		
		public int insertUserTag(TagDataBean tagDto) {
			return session.insert("tag.insertUserTag", tagDto);
		}
		public TagDataBean getTagByValue(String tag_value) {
			return session.selectOne("tag.getTagByvalue",tag_value);
		}
		
		public String tagIdtoTagName1(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName1", sendNum1);
		}
		public String tagIdtoTagName2(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName2", sendNum1);
		}
		public String tagIdtoTagName3(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName3", sendNum1);
		}
		public String tagIdtoTagName4(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName4", sendNum1);
		}
		public String tagIdtoTagName5(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName5", sendNum1);
		}
		public String tagIdtoTagName6(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName6", sendNum1);
		}
		public String tagIdtoTagName7(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName7", sendNum1);
		}
		public String tagIdtoTagName8(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName8", sendNum1);
		}
		public String tagIdtoTagName9(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName9", sendNum1);
		}
		public String tagIdtoTagName10(Map<String, Integer> sendNum1){
			return session.selectOne("tag.tagNumToName10", sendNum1);
		}
}