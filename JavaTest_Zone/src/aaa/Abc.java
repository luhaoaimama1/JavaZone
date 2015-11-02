package aaa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Abc<Abc>  implements Iterable<Abc>{
	public void aa(){
		StackTraceElement[] ste = new Throwable().getStackTrace();
		for (StackTraceElement a : ste) {
			System.out.println(a);
		}
		
	}

	@Override
	public Iterator<Abc> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
