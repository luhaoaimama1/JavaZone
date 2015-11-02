package Android.Zone.Sqlite.Annotation.utils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import Android.Zone.Sqlite.Annotation.Column;
import Android.Zone.Sqlite.Annotation.Table;
import Android.Zone.Sqlite.GsonEntity.GsonColumn;
import Android.Zone.Sqlite.GsonEntity.GsonTop;

public class AnUtils {

	public static String getGsonStr(Class<?> t) {
		GsonTop gsonTop = new GsonTop();
		Field[] fields = t.getDeclaredFields();
		List<GsonColumn> gsonColumnList=new ArrayList<GsonColumn>();
		for (Field item : fields) {
			GsonColumn gsonColumn = new GsonColumn();
			gsonColumn.setName(getAnnoColumn_Name_ByField(item, t));
			gsonColumn.setLength(getAnnoColumn_Length_ByField(item, t));
			gsonColumnList.add(gsonColumn);
		}
		gsonTop.setData(gsonColumnList);
		String tempStr =new Gson().toJson(gsonTop);
		return tempStr;
	}
	/**
	 * 得到注解的表名  如果没有 就getSimpleName
	 * @param t
	 * @return
	 */
	public static String getTableAnnoName(Class<?> t){
		String temp=null;
		Table anno =t.getAnnotation(Table.class);
		if(anno!=null){
			temp=anno.name();
			if("".equals(temp)){
				throw new IllegalStateException("注解column 不能为 空字符串");
			}
		}else{
			temp=t.getSimpleName();
		}
		return temp;
	}
	//TODO  
	public static void getColumnAnno(){
		
	}
	
	/**
	 * 这个是  增删改查 用的
	 * 
	 * 即重字符串''中取出 _abc_  并替换成 abc  
	 * 没有注解呢 即哟弄个原来的 即abc
	 */
	public static String replaceByAnnoColumn(String abc,Class<?> t) {
		Pattern pa = Pattern.compile("_.*?_");
		Matcher m = pa.matcher(abc);
		String tempStr=abc;
		while (m.find()) {
			String olderChar = m.group();
			String newChar = olderChar.substring(1, olderChar.length() - 1);
			//这里用注解改成newChar 这里_?_ 只有一个 即不用分割
			Column annoStr=null;
			try {
				 Field field = t.getDeclaredField(newChar);
				 annoStr =field.getAnnotation(Column.class);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			if(annoStr!=null){
				newChar=annoStr.column();
			}
			tempStr=tempStr.replace(olderChar, newChar);
		}
		return tempStr;
	}
	public static String getAnnoColumn_Name_ByField(Field f,Class<?> t){
		String temp=null;
		Column anno = f.getAnnotation(Column.class);
		if(anno!=null){
			temp=anno.column();
			//TODO  但注解是""的时候呢  有这么恶心的  我就抛出异常 让他恶心我
			if("".equals(temp)){
				throw new IllegalStateException("注解column 不能为 空字符串");
			}
		}else{
			temp=f.getName();
		}
		return temp;
	}
	public static String getAnnoColumn_Length_ByField(Field f,Class<?> t){
		String temp=null;
		Column anno = f.getAnnotation(Column.class);
		if(anno!=null){
			temp=anno.length();
		}else{
			temp="100";
		}
		return temp;
	}
	
}
