package Java.Zone.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldTypeUtils {

	public static FieldTypeInfo getFieldTypeInfo(Field field) {
		FieldTypeInfo fieldType=new FieldTypeInfo();
		try {
			ParameterizedType type = (ParameterizedType) field.getGenericType();
//			解析后的结果
//			List<Top_data> data [0]Top_data
//			Map<String,Top_data> [0]String[1]Top_data
			Type rawType = type.getRawType();
			fieldType.rawType=rawType;
			fieldType.erasureTypes=Arrays.asList(type.getActualTypeArguments()) ;
			fieldType.isErasureType=true;
		} catch (Exception e) {
			Type type = field.getGenericType();
			fieldType.rawType=type;
		}
		return fieldType;
	}

	public static class FieldTypeInfo {
		public boolean isErasureType=false;
		public Type rawType;
		public List<Type> erasureTypes=new ArrayList<Type>();
		@Override
		public String toString() {
			return super.toString();
		}
	}
}
