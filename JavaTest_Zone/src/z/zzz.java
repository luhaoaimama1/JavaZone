package z;

import java.util.ArrayList;
import java.util.List;

public class zzz {
	static int a = 0;
	public static void main(String[] args) {
		List<Integer> b=new ArrayList<Integer>();
		b.add(a);
		a=1;
		b.add(a);
		b.add(a);
		b.add(a);
		for (Integer integer : b) {
			System.out.println(integer);
		}
	}
}
