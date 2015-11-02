import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class aa2 {
	static String a="{    \",:code }";
	public static void main(String[] args) {
		String b=a.replace(" ", "");
		System.out.println(b);
		Pattern pa = Pattern.compile("\\{\",\\:.*");
		Matcher m = pa.matcher(b);
		if (m.matches()) {
				System.out.println("true");
		}else{
			System.out.println("false!s");
		}
	}
			
}
