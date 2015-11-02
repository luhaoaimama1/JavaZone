package bb; 

import java.util.HashMap;
import java.util.Map.Entry;

public class aaaaa {
	public static void main(String[] args) {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("a", "1");
		map.put("a", null);
		System.out.println("zhe"+map.size());
		System.out.println(map.get("a"));
		map.remove("a");
		System.out.println(map.get("a"));
		for (Entry<String, String> string : map.entrySet()) {
			System.out.println("aaaaa"+string.getKey());
		}
		System.out.println(map.size());
	}
}
