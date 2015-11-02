
public class Test {
	public static class F{
		public int i;
	}
	public static void main(String[] args) {
		Test t = new Test();
		F f = new F();
		f.i=1;
		F f2 = new F();
		System.out.println(f2.i);
				
	}

}
