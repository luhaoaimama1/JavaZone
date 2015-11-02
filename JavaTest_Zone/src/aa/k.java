package aa;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class k implements bInter {
	private k() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gan() throws UnsupportedEncodingException {
		System.out.println("k");
//		new String(new byte[]{'a','b'}, "utf-8");
		 List<String> stringList=new ArrayList<String>();
		 for ( String string : stringList) {
			System.out.println(string);
		}

	}

	@Override
	public bInter getInstance() {
		return new k();
	}

	public static void main(String[] args) {
		String a=""+16*2+"dp";
		System.out.println(a);
	}
}
