package self.kiwi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONArray;

import com.mongodb.BasicDBObject;

public class Transformer {
	public static BasicDBObject Hashmap2DBObject(HashMap<String, String> hashMap){
		BasicDBObject object = new BasicDBObject();
		Iterator<Entry<String, String>> iterator = hashMap.entrySet().iterator();
		while (iterator.hasNext()){
			Entry<String, String> entry = (Entry<String, String>)iterator.next();
			object.append(entry.getKey(),entry.getValue());
		}
		return object;
	}
	
	public static JSONArray array2Json(ArrayList<String> arrayList){
		JSONArray jsonArray = new JSONArray(arrayList);
		return jsonArray;
	}
}
