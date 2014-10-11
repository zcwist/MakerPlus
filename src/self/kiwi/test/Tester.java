package self.kiwi.test;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.json.JSONArray;

import self.kiwi.config.RootPath;
import self.kiwi.dao.EventDAO;
import self.kiwi.dao.MemberDAO;
import self.kiwi.event.RegisterEvent;
import self.kiwi.model.GenericMember;
import self.kiwi.util.Transformer;
import self.kiwi.util.XMLUtil;

import com.mongodb.BasicDBObject;

public class Tester {

	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tester().queryDemo();

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
	
	public void getMemberInfo(){
		try {
			EventDAO eventDAO = new EventDAO();
			System.out.println(eventDAO.queryMemberInfoByMemberId("5436019596bcae3914718228"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void queryDemo(){
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(date);
		
		BasicDBObject queryObj = new BasicDBObject();
		Pattern pattern = Pattern.compile("Oct 09"); 
		queryObj.put("EventDate", pattern);
		try {
			EventDAO eventDAO = new EventDAO();
			JSONArray idList = eventDAO.queryEvent(queryObj);
			System.out.println(idList.toString());
			System.out.println(eventDAO.queryMemberListInEvent(idList));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
