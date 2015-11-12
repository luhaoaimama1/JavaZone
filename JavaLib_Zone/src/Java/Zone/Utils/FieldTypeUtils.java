package Java.Zone.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import Java.Zone.Log.PrintUtils;

public class FieldTypeUtils {

	public static FieldType getFieldType(Field field) {
		FieldType fieldType=new FieldType();
		try {
			ParameterizedType type = (ParameterizedType) field.getGenericType();
			Type temp = type.getActualTypeArguments()[0];
//			System.out.println(field.getName() + " ___ParameterizedType___ \t "
//					+ type.getActualTypeArguments()[0]);
			fieldType.fieldProperty=FieldProperty.LIST_CLASS;
			fieldType.classStr=temp.toString();
			if(String.class.toString().equals(temp.toString())){
				fieldType.fieldProperty=FieldProperty.LIST_STRING;
			}
			// บรสน
			// Class<?> lin =
			// Class.forName(type.getActualTypeArguments()[0].toString());
			// Parent a = (Parent) lin.newInstance();
		} catch (Exception e) {
			Type type = field.getGenericType();
			fieldType.classStr=type.toString();
			fieldType.fieldProperty=FieldProperty.CLASS;
//			System.out.println(field.getName() + "\t " + type);
			if(String.class.toString().equals(type.toString())){
				fieldType.fieldProperty=FieldProperty.STRING;
			}
		}
		return fieldType;
	}

	public static class FieldType {
		public String classStr;
		public FieldProperty fieldProperty;
		@Override
		public String toString() {
			PrintUtils.printAllField(this);
			return super.toString();
		}
	}
	public enum FieldProperty{
		STRING,CLASS,LIST_STRING,LIST_CLASS;
	}
}
