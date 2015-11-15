package gson学习与反射.反射;

public class 基本类型的判断 {
	public static void main(String[] args) throws Exception {
		System.out.println((char) 65);
		System.out.println(isWrapClass(Long.class));
		System.out.println(isWrapClass(int.class));
		System.out.println(isWrapClass(Integer.class));
		System.out.println(isWrapClass(String.class));
		System.out.println(isWrapClass(基本类型的判断.class));
		System.out.println("曰："+Integer.class.isPrimitive());
		System.out.println("曰："+int.class.isPrimitive());
		System.out.println("曰："+void.class.isPrimitive());
	}

	public static boolean isWrapClass(Class clz) {
		try {
			return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}
}
