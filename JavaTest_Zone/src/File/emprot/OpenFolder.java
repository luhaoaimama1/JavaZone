package File.emprot;

import java.io.IOException;

public class OpenFolder {
	public static void main(String[] args) {
		
//		openFolder("D:/JSONtest");
	}
	public  static void openFolder(String folderPath){
		try {
			Runtime.getRuntime().exec("cmd /c start "+folderPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
