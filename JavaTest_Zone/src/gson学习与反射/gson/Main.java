package gson学习与反射.gson;
import gson学习与反射.entity.Top;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import Java.Zone.Utils.FieldTypeUtils;
import Java.Zone.Utils.FieldTypeUtils.FieldTypeInfo;
import Java.Zone.Utils.TypeUtils;

import com.google.gson.Gson;

import json.IOUtils;

public class Main {
	public static void main(String[] args) {
		File file=new File("C:/Users/123/Desktop/json例子/json2.txt");
		String str = IOUtils.read(file, "gbk");
		Top top = new Gson().fromJson(str, Top.class);
		System.out.println(top);
		//反射出来所有类
		try {
			Top to = top.getClass().newInstance();
		
			for (Field item :top.getClass().getDeclaredFields()) {
				System.out.println(item.getType());
				item.setAccessible(true);
				FieldTypeInfo temp = FieldTypeUtils.getFieldTypeInfo(item);
				if(temp.isErasureType){
					System.out.print(temp.rawType+"______泛型参数：___");
					for (Type string : temp.erasureTypes) {
						System.out.println("index:"+temp.erasureTypes.indexOf(string)+"___"+TypeUtils.toClass(string).getName());
					}
				}else{
					System.out.println(temp.rawType+"_________"+TypeUtils.toClass(temp.rawType).getName());
				}
				if(temp.isErasureType){
					//这个就不写了
				}else{
					if(!TypeUtils.isPrimitive(temp.rawType)&&!TypeUtils.isPrimitiveWrap(temp.rawType)){
						for (Field to_temp_item : TypeUtils.toClass(temp.rawType).getDeclaredFields()) {
							FieldTypeInfo to_temp_itemType = FieldTypeUtils.getFieldTypeInfo(to_temp_item);
							//竟然输出出来了
							System.out.println("cao:"+ to_temp_itemType.rawType);
						}
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
