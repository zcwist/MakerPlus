package self.kiwi.dao;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.json.JSONArray;
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
	public JSONArray queryEvent(BasicDBObject queryObject){
		JSONArray idList = new JSONArray();
		DBCollection coll = db.getCollection(COLLNAME);
		DBCursor cursor = coll.find(queryObject);
		while (cursor.hasNext()) {
			DBObject item = cursor.next();
			
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

}
