import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class abababaab {
	public static void main(String[] args) {
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("1", "D:/新建文本文档.txt");
		if (new File("D:/新建文本文档.txt").exists()) {
			System.out.println("存在");
		} else {
			System.out.println("no");
		}
		mosicFileMapDelectCache(fileMap);
		
	}

	public static void mosicFileMapDelectCache(Map<String, String> fileMap) {
		for (Entry<String, String> item : fileMap.entrySet()) {
			if (item.getValue() != null&&item.getValue().contains("建")) {
				File file = new File(item.getValue());
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}
}
