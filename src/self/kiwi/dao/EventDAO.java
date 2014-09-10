package self.kiwi.dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

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

}
