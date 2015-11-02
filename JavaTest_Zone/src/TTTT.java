import java.io.File;

import json.IOUtils;

public class TTTT {
	public static void main(String[] args) {
		File f1 = new File("./src/ABK/kb.txt");
		System.out.println(f1.getAbsolutePath());
//		/Zone_JavaTest/src/ABK/kb.txt
		System.out.println(f1.exists());
		System.out.println(IOUtils.read(f1, "utf-8"));
	}
}
