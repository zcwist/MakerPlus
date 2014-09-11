package self.kiwi.event;

import java.util.ArrayList;
import java.util.HashMap;

import self.kiwi.dao.MemberDAO;
import self.kiwi.util.Transformer;
import self.kiwi.util.XMLUtil;

import com.mongodb.BasicDBObject;

public class RegisterEvent extends AbstractEvent{

	public RegisterEvent() {
		// TODO Auto-generated constructor stub
		this.eventName = "registerevent";
		this.addExp = "1";
	}

	@Override
	public void runEvent(HashMap<String, String> parameterMap) {
		// TODO Auto-generated method stub
		BasicDBObject object = Transformer.Hashmap2DBObject(parameterMap);
		
		try{
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.insertNewMember(object);
			memberDAO.destroy();
			this.userID = object.get("_id").toString();
		} catch (Exception e){
			e.printStackTrace();
		} 	
		super.registerEvent();
	}
	

	@Override
	public ArrayList<String> getParameterList() {
		// TODO Auto-generated method stub
//		ArrayList<String> al = new ArrayList<String>();
//		Field[] fields = MemberInfo.class.getFields();
//		for (int i = 0; i < fields.length; i++){
//			al.add(fields[i].getName());
//		}
//		return al;
		return XMLUtil.getParamListByEventName("registerEvent");
	}
	
	

}
