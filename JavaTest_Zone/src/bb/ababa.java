package bb;

import java.util.HashMap;
import java.util.Map.Entry;

public class ababa {
	public static void main(String[] args) {
		HashMap<String, String> map=new HashMap<String,String>();
		map.put("iv1", "aaaa");
		map.put("iv2", "bbbb");
		map.put("iv3", "cccc");
		map.put("iv4", "ddd");
		map=aa(map, "iv2");
		map=aa(map, "iv2");
		for (Entry<String, String> string : map.entrySet()) {
			System.out.println("key:"+string.getKey()+"\t"+string.getValue());
		}
	}
	public static HashMap<String, String> aa(HashMap<String, String> map,String key)
	{
		int index= Integer.parseInt(key.substring(2));
		for (int i =index; i <=map.size(); i++) {
			String keyCont=key.substring(0,2)+i;
			String keyContAddOne=key.substring(0,2)+(i+1);
			map.put(keyCont, map.get(keyContAddOne));
		}
		return map;
	}
}
