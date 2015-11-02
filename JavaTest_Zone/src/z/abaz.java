package z;

import java.util.LinkedList;

public class abaz {

	public static void main(String[] args) {
		LinkedList<String> ll=new LinkedList<String>();
		ll.add("1");
		ll.add("2");
		ll.add("3");
		ll.add("4");
		ll.add("5");
		ll.add(2, "66");
		ll.remove(1);
		for (String string : ll) {
			System.out.println(string);
		}
	}

}
