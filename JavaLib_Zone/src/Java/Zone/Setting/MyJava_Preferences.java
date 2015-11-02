package Java.Zone.Setting;

import java.net.URL;

/**
 * 内部包的参数设置 与方法设置
 * 
 * @author Zone
 * 
 */
public class MyJava_Preferences {
	public static String a="b";

	/**
	 * 每个方法的开始 用这个 log容易观察
	 */
	public static void op() {
//		StackTraceElement ste = new Throwable().getStackTrace()[1];
//		String printStr = ste.toString();
//		System.err.println("---------------" + printStr+ "开始-------------------------");
	}
	/**
	 * 每个方法的结束 用这个 log容易观察
	 */
	public static void ed() {
//		StackTraceElement ste = new Throwable().getStackTrace()[1];
//		String printStr = ste.toString();
//		System.err.println("---------------" + printStr+ "结束-------------------------");
	}
	/**
	 * 调用内部方法：开始
	 */
	public static void InnerOp() {
//		System.err.println("---------------调用内部方法:↓开始-------------------------");
	}
	/**
	 * 调用内部方法：结束
	 */
	public static void InnerEd() {
//		System.err.println("---------------调用内部方法:↑结束-------------------------");
	}
	
	
	/**
	 * 
	 * @return 得到当前的类名
	 */
	public static String getClassName(){
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		String[] s=ste.getClassName().split("[.]");
		return s[s.length-1]+"类:";
	}
	/**
	 * 方法1
	 * Java端OK 
	 * <br>Android断 不好使
	 * @return  调用此方法类的项目名字
	 */
	public static String getProjectName() {
//		StackTraceElement[] a=new Throwable().getStackTrace();
//		int j=0;
//		for (StackTraceElement stackTraceElement : a) {
//			System.out.println("j:"+j+"_====="+stackTraceElement.toString());
//			j++;
//		}
		StackTraceElement st = new Throwable().getStackTrace()[1];
		String className = st.getClassName();
		System.out.println("className—>:" + st.getClassName());
		String classNamePath = className.replace(".", "/") + ".class";
		System.out.println("classNamePath—>:" + classNamePath);
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		URL is = cl.getResource(classNamePath);
		String path = is.getPath();
		String[] k=path.split("[/]");
		for (int i = 0; i < k.length; i++) {
			if ("bin".equals(k[i])) {
				return k[i - 1];
			}
		}
		return null;
	}
	/**
	 * 方法2
	 * Java端OK 
	 * <br>Android断 不好使
	 * @return  调用此方法类的项目名字
	 */
	public static String getProjectName2() {
		StackTraceElement st = new Throwable().getStackTrace()[1];
		String className = st.getClassName();
		URL is=null;
		try {
			System.out.println("className—>:" + st.getClassName());
			System.out.println("这个名字："+Class.forName(className).getClassLoader().getResource(""));
			is = Class.forName(className).getClassLoader().getResource("");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("类为找到异常");
		}
		String path = is.getPath();
		String[] k=path.split("[/]");
		for (int i = 0; i < k.length; i++) {
			if ("bin".equals(k[i])) {
				return k[i - 1];
			}
		}
		return null;
	}
}
