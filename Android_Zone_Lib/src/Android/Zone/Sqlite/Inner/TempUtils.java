package Android.Zone.Sqlite.Inner;

import java.lang.reflect.Type;

public class TempUtils {
	/**
	 * 例子：typeIsNotEquals(String.class, field.getGenericType())
	 * @param cl  String.class
	 * @param type  反射里的Type
	 * @return  是否相同
	 */
	public static boolean typeIsEquals(Class cl,Type type){
		if(cl.toString().equals(type.toString())){
			return true;
		}
		return false;
	}
}
