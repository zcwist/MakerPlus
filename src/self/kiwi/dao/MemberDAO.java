package self.kiwi.dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import self.kiwi.model.GenericMember;

public class MemberDAO extends MongoWrapper {
	private final String COLLNAME = "member_list_coll";

	public MemberDAO() throws UnknownHostException {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public int insertNewMember(GenericMember newMember){
		try{
		DBCollection coll = db.getCollection(COLLNAME);
		coll.insert(newMember.getMemberObj());
		} catch (Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public int insertNewMember(BasicDBObject object){
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
