package self.kiwi.util;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import self.kiwi.config.RootPath;

public class XMLUtil {
	public static ArrayList<String> getParamListByEventName(String eventName){
		ArrayList<String> al = new ArrayList<String>();
		try{
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			String path = RootPath.getInstance().getRoot();
			doc = builder.parse(new File(path + "/WEB-INF/EventConfig.xml"));

			NodeList nl = doc.getElementsByTagName("event");

			for (int i = 0; i < nl.getLength(); i++){
				Element element = (Element)nl.item(i);
				NodeList eventNameNodeList = element.getElementsByTagName("eventName");
				String eventNameString = eventNameNodeList.item(0).getFirstChild().getNodeValue();
				if (eventNameString.equalsIgnoreCase(eventName)){
					
					NodeList paramsList = element.getElementsByTagName("parameter");
					for (int j = 0; j < paramsList.getLength(); j++){
						String params = paramsList.item(j).getFirstChild().getNodeValue();
						al.add(params);
						
					}
					return al;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

}
