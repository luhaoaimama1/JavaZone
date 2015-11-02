import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zhengze {

	public static void main(String[] args) {
		String a = "{    \"code\": 0,\"data\":\"[bab}\"    }";
		codeDataPattern(a);
	}

	public static boolean codeDataPattern(String a) {
		String b = a.replace(" ", "");
		System.out.println(b);
		Pattern pa = Pattern.compile("[{]\"code\":[0-9]+,\"data\":\"[^{\\[].*\"[}]");
		Matcher m = pa.matcher(b);
		if (m.matches()) {
			System.out.println("true");
			return true;
		} else {
			System.out.println("false!");
			return false;
		}
	}
}
