package self.kiwi.model;

import com.mongodb.BasicDBObject;

public class GenericMember {
	private BasicDBObject memberObj;

	public GenericMember(String id, String name) {
		// TODO Auto-generated constructor stub
		memberObj = new BasicDBObject().append("id", id).append("name", name);
	}
	public BasicDBObject getMemberObj(){
		return memberObj;
		
	}

}
