package db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;
import db.TagDataBean;

public class TagDBBean {
		private SqlSession session=SqlMapClient.getSession();
		
		public List<String> getB_tags(int board_no) {
			return session.selectList("board.getBoardTags", board_no);
		}
		public List<String> getUser_tags(String user_id) {
			return session.selectList("user.getUserTags", user_id);
		}
		
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
		public int getCount() {
			return session.selectOne("tag.getTCount");
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
			List<TagDataBean> userTagList=session.selectList("db.getUserTags", user_id);
			for(int i=0; i<userTagList.size(); i++) {
				userTagList.get(i).setTag_value((String)session.selectOne("db.getTagValue", userTagList.get(i).getTag_id()));
			}
			return userTagList;
		}
		public List<TagDataBean> getTripTags(int board_no) {
			return session.selectList("board.getTripTags", board_no);	// need to check after making boardDB.xml
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
			List<TagDataBean> oldTripTags=getTripTags(board_no);
			//setter for query
			Map<String, Integer> tagSetter;
			for(int i=0; i<oldTripTags.size(); i++) {
				//put tag values by tag id
				oldTripTags.get(i).setTag_value((String)session.selectOne("db.getTagValue", oldTripTags.get(i).getTag_id()));			
			}
			//tester to check whether trip already has that tag
					boolean hasTag=false;
					//Check if the trip had these tags 
					for(TagDataBean board:tripTags) {
						hasTag=false;
						for(TagDataBean otb:oldTripTags) {
							//if user didn't have such like a tag
							if(otb.getTag_id()==(board.getTag_id())) {
								hasTag=true;
							}
						}
						//if user didn't have this tag, insert it!
						if(!hasTag) {
							tagSetter=new HashMap<String, Integer>();
							tagSetter.put("board_no", board_no);
							int tagId= board.getTag_id();
							tagSetter.put("tag_id", tagId);
							result=session.update("db.updateTripTags", tagSetter);
						}
					}
					//user had that tag, but not anymore! 
					for(TagDataBean otb:oldTripTags) {
						hasTag=false;
						for(TagDataBean tb:tripTags) {
							if(otb.getTag_id()==(tb.getTag_id())) {
								hasTag=true;
							}
						}
						//there is no such tag what was in old tag list! So delete it!
						if(!hasTag) {
							tagSetter=new HashMap<String, Integer>();
							tagSetter.put("board_no", board_no);
							int tagId=otb.getTag_id();
							tagSetter.put("tag_id", tagId);
							result=session.delete("db.deleteTripTag", tagSetter);
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
			
			for(int i=0; i<oldUserTags.size(); i++) {
				//put tag values by tag id
				oldUserTags.get(i).setTag_value((String)session.selectOne("tag.getTagValue", oldUserTags.get(i).getTag_id()));
			}
			
			//tester to check whether user already has that tag
			boolean hasTag=false;
			//Did user have this tag?
			for(TagDataBean tb:userTags) {
				hasTag=false;
				for(TagDataBean otb:oldUserTags) {
					//if user didn't have such like a tag
					if(otb.getTag_id()==(tb.getTag_id())) {
						hasTag=true;
					}
				}
				//if user didn't have this tag, insert it!
				if(!hasTag) {
					tagSetter=new HashMap<String, String>();
					tagSetter.put("user_id", user_id);
					String tagId=""+tb.getTag_id();
					tagSetter.put("tag_id", tagId);
					result=session.update("tag.updateUserTag", tagSetter);
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
					String tagId=""+otb.getTag_id();
					tagSetter.put("tag_id", tagId);
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
	
}