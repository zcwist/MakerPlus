package self.kiwi.dao;

import java.net.UnknownHostException;

import org.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class EventDAO extends MongoWrapper {
	private final String COLLNAME = "event_list_coll";

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

}
