package staticUtils顺序;

public class Us {
	static int  i=1;
	static {
		i=2;
		System.out.println(i);
		System.out.println("static　前面的");
	}
	public static void gaga(){
		System.out.println("gaga");
	}
	static {
		System.out.println("static　后边的");
	}


}
