package self.kiwi.test;

import java.util.HashMap;

import self.kiwi.config.RootPath;
import self.kiwi.dao.MemberDAO;
import self.kiwi.event.RegisterEvent;
import self.kiwi.model.GenericMember;
import self.kiwi.util.Transformer;
import self.kiwi.util.XMLUtil;

public class Tester {

	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tester().jsonTester();

	}
	
	public void addUser(){
		GenericMember newMember = new GenericMember("111", "zcw");
		try{
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.insertNewMember(newMember);
			memberDAO.destroy();
		} catch (Exception e){
			e.printStackTrace();
		} 
	}
	
	public void eventTester(){
		RegisterEvent registerEvent = new RegisterEvent();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		for (String parameter:registerEvent.getParameterList()){
			hashMap.put(parameter, "kiwi");
		}
		registerEvent.runEvent(hashMap);
		System.out.println(registerEvent.getUserID());
		System.out.println(registerEvent.getAddExp());
		System.out.println(registerEvent.getEventName());
		System.out.println(registerEvent.getEventDate());
		
	}
	public void XMLUtilTester(){
		
//		System.out.println(XMLUtil.getParamListByEventName("registerEvent").size());
		System.out.println(XMLUtil.getEventList().get(0));
	}
	
	public void jsonTester(){
		RootPath.getInstance().setRoot("WebRoot");
		System.out.println(Transformer.array2Json(XMLUtil.getEventList()).toString());
	}

}
