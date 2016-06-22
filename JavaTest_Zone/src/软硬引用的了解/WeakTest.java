package 软硬引用的了解;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakTest {
	static List<WeakReference <Ga>> list=new ArrayList<>();
	public static void main(String[] args) {
		String abc = new String("abc");
		abc = new String("abcd");
//		abc ="abcd";//这样声明   会导致 有普通的引用指向那个对象
		WeakReference<String> abcWeakRef = new WeakReference<String>(abc);
		abc = null;
		System.out.println("before gc: " + abcWeakRef.get());
		System.gc();
		System.out.println("after gc: " + abcWeakRef.get());
		
		//不可达 demo
		Ga ga = new Ga();
		list.add(new WeakReference <Ga>(ga));
		System.out.println("yue:"+list.get(0).get());
		ga=null;
		System.out.println("yue:"+list.get(0).get());
		System.gc();
		System.out.println("yue:"+list.get(0).get());
		
		//超出作用于的demo
		method();
		System.out.println("scope:"+list.get(1).get());
		System.gc();
		System.out.println("scope:"+list.get(1).get());
		
		
	}
	public static void method(){
		Ga ga = new Ga();
		list.add(new WeakReference <Ga>(ga));
		
	}
	public static class Ga{
		public String name="a";
	}

}
