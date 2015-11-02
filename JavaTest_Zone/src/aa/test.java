package aa;

public class test {
	private static String abc = "bad";
	private static String abcd = null;

	public static void main(String[] args) {
		String[] a = new String[] { "b", "k" };
		System.out.println(test.abcD());
		System.out.println(abc.hashCode());
		System.out.println(abcd.hashCode());
	}
	public static String getAbc() {
		return abc;
	}

	public static void setAbc(String abc) {
		test.abc = abc;
	}
	public static String abcD(){
		StackTraceElement temp = new Throwable().getStackTrace()[0];
		return temp.getMethodName();
	}
	

}
