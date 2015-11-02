import java.io.File;

public class bababab {
	public static void main(String[] args) {
		File f=new File("D:/邮箱.txt");
		if(f.exists()){
			System.out.println(f.getName());
		}
	}
}
