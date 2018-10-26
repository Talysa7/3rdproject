/*
 * !!! ���ǻ��� !!!
 * ���� void, ���� �������� �޼ҵ� �̸��� �����ص� ����.
 * �޼ҵ帶�� ���� ��, ���� �˾Ƽ� ä�� ���� ��.
 */

package db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.SqlMapClient;

public class TagDBBean {
	SqlSession session=SqlMapClient.getSession();
	
	//add tag
	public int insertTag(String tag_value) {
		return session.insert("db.insertTag",tag_value);
	}
	public int insertCityTag(String tag_value) {
		return session.insert("db.insertCityTag",tag_value);
	}
	//tag list
	public  List<TagDataBean> getTags(Map<String, Integer> map){
		return session.selectList("db.getTags",map);	
	}
	//total tag
	public int getCount() {
		return session.selectOne("db.getTCount");
	}
	//check tag
	public int checkTag(String tag_value) {
		return session.selectOne("db.checkTag",tag_value);
	}
	//show tag info
	public TagDataBean getTag(int tag_id) {
		return session.selectOne("db.getTag",tag_id);
	}
	//modify tag
	public int modifyTag(TagDataBean tagDao) {
		return session.update("db.modifyTag",tagDao);
	}
	//delete tag
	public int deleteTag(String tag_id) {
		return session.delete("db.deleteTag",tag_id);
	}
	public List<TagDataBean> getUserTags(String user_id) {
		List<TagDataBean> userTagList=session.selectList("db.getUserTags", user_id);
		for(int i=0; i<userTagList.size(); i++) {
			userTagList.get(i).setTag_value((String)session.selectOne("db.getTagValue", userTagList.get(i).getTag_id()));
		}
		return userTagList;
	}
	public List<TagDataBean> getTripTags(int tb_no) {
		return session.selectList("db.getTripTags", tb_no);
	}
	public List<TagDataBean> getStyleTags() {
		return session.selectList("db.getStyleTags");
	}
	public List<TagDataBean> getCityTags() {
		return session.selectList("db.getCityTags");
	}
	public List<TagDataBean> getCountryTags() {
		return session.selectList("db.getCountryTags");
	}
	public int getTagId(String tag_value) {
		return session.selectOne("db.getTagId", tag_value);
	}
	public int updateTripTags(int tb_no, List<TagDataBean> tripTags) {
		int result=1;
		//set old Trip Tags
		List<TagDataBean> oldTripTags=getTripTags(tb_no);
		//setter for query
		Map<String, Integer> tagSetter;
		for(int i=0; i<oldTripTags.size(); i++) {
			//put tag values by tag id
			oldTripTags.get(i).setTag_value((String)session.selectOne("db.getTagValue", oldTripTags.get(i).getTag_id()));			
		}
		//tester to check whether trip already has that tag
				boolean hasTag=false;
				//Check if the trip had these tags 
				for(TagDataBean tb:tripTags) {
					hasTag=false;
					for(TagDataBean otb:oldTripTags) {
						//if user didn't have such like a tag
						if(otb.getTag_id()==(tb.getTag_id())) {
							hasTag=true;
						}
					}
					//if user didn't have this tag, insert it!
					if(!hasTag) {
						tagSetter=new HashMap<String, Integer>();
						tagSetter.put("tb_no", tb_no);
						int tagId= tb.getTag_id();
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
						tagSetter.put("tb_no", tb_no);
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
			oldUserTags.get(i).setTag_value((String)session.selectOne("db.getTagValue", oldUserTags.get(i).getTag_id()));
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
				result=session.update("db.updateUserTag", tagSetter);
			}
		}
		//user had that tag, but not anymore! 
		for(TagDataBean otb:oldUserTags) {
			hasTag=false;
			for(TagDataBean tb:userTags) {
				if(otb.getTag_id()==(tb.getTag_id())) {
					hasTag=true;
				}
			}
			//there is no such tag what was in old tag list! So delete it!
			if(!hasTag) {
				tagSetter=new HashMap<String, String>();
				tagSetter.put("user_id", user_id);
				String tagId=""+otb.getTag_id();
				tagSetter.put("tag_id", tagId);
				result=session.delete("db.deleteUserTag", tagSetter);
			}
		}
		return result;
	}
	public String getTagValue(int tag_id) {
		return session.selectOne("db.getTagValue", tag_id);
	}
	public int setTripTag(Map<String, Integer> tagSetter) {
		return session.update("db.insertTripTags", tagSetter);
	}
}
