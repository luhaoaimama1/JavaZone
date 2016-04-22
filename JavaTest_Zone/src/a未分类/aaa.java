package a未分类;

public class aaa {
	public static boolean isEmptyTrim(String str) {
		return str == null ||( str.length() == 0 || str.trim().length() == 0);
	}
	public static void main(String[] args) {
		
		System.out.println(isEmptyTrim(null));
		System.out.println(isEmptyTrim(""));
		System.out.println(isEmptyTrim("   "));
	}
}
