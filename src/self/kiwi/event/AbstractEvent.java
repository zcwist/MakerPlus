package self.kiwi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import self.kiwi.dao.EventDAO;
import self.kiwi.util.Transformer;

import com.mongodb.BasicDBObject;

public abstract class AbstractEvent {
	protected String eventName;
	protected String addExp;
	private String eventDate;
	protected String userID;
	private BasicDBObject memberInfo;
	

	public AbstractEvent() {
		// TODO Auto-generated constructor stub
		this.eventDate = new Date().toString();
	}

	public String getEventName() {
		return eventName;
	}


	public String getAddExp() {
		return addExp;
	}


	public String getEventDate() {
		return eventDate;
	}

	public String getUserID() {
		return userID;
	}
	
	public BasicDBObject getMemberInfo(){
		return memberInfo;
	}
	
	public void registerEvent(){
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("EventName", eventName);
		hashMap.put("EventDate", eventDate);
		hashMap.put("UserID", userID);
		hashMap.put("AddExp", addExp);
		BasicDBObject object = Transformer.Hashmap2DBObject(hashMap);
		try{
			EventDAO eventDAO = new EventDAO();
			eventDAO.insertNewEvent(object);
			//get member info from member_list_coll
			memberInfo = (BasicDBObject) eventDAO.queryMemberInfoByMemberId(userID);
			eventDAO.destroy();
		} catch (Exception e){
			e.printStackTrace();
		} 
		
	}
	
	public ArrayList<String> getParameterList(){
		return null;
	}
	
	public void runEvent(HashMap<String,String> parameterMap){
		registerEvent();
	}


}
