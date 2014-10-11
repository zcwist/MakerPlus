package self.kiwi.dao;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class EventDAO extends MongoWrapper {
	private final String COLLNAME = "event_list_coll";
	private final String USERCOLL = "member_list_coll";

	public EventDAO() throws UnknownHostException {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public int insertNewEvent(BasicDBObject object){
		try{
				DBCollection coll = db.getCollection(COLLNAME);
				coll.insert(object);
			} catch (Exception e){
				e.printStackTrace();
				return 0;
			}
			
			return 1;
	
	}
	
	//针对Event查id号
	public JSONArray queryEvent(BasicDBObject queryObject){
		JSONArray idList = new JSONArray();
		DBCollection coll = db.getCollection(COLLNAME);
		DBCursor cursor = coll.find(queryObject);
		while (cursor.hasNext()) {
			DBObject item = cursor.next();
			idList.put(item.get("UserID"));
//			DBObject member = queryMemberInfoByMemberId(item.get("UserID").toString());
//			BasicDBObject nameAndPhone = new BasicDBObject();
//			nameAndPhone.put("name",member.get("姓名"));
//			String phone = member.get("电话").toString();
//			String phoneWithStar = phone.substring(0, 3) + "maker" + phone.substring(8,11);
//			nameAndPhone.put("phone", phoneWithStar);
//			idList.put(nameAndPhone);
			
		}
			
		return idList;
	}
	
	public DBObject queryMemberInfoByMemberId(String userId){
		BasicDBObject query = new BasicDBObject();
		ObjectId id = new ObjectId(userId);
		query.put("_id", id);
		DBCollection coll = db.getCollection(USERCOLL);
		return coll.findOne(query);
	}
	
	public JSONArray queryMemberListInEvent(JSONArray idList){
		JSONArray memberList = new JSONArray();
		DBCollection coll = db.getCollection(USERCOLL);
		for (int i=0; i<idList.length();i++){
			try {
				ObjectId id = new ObjectId(idList.get(i).toString());
				BasicDBObject query = new BasicDBObject();
				query.put("_id", id);
				
				DBObject member = (BasicDBObject) coll.findOne(query);
				BasicDBObject nameAndPhone = new BasicDBObject();
				System.out.println(member);
				try{
					nameAndPhone.put("name",member.get("姓名"));
				}
				catch(Exception e){
					System.out.println(id.toString());
				}
				String phone = member.get("电话").toString();
				String phoneWithStar = phone.substring(0, 3) + "maker" + phone.substring(8,11);
				nameAndPhone.put("phone", phoneWithStar);
				
				memberList.put(nameAndPhone);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return memberList;
	}
	
	

}
