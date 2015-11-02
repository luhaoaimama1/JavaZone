package json;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import json.EntityEX.ClassEntity;
import json.EntityEX.ListEntity;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 1.分析： 效验 验证正确性  	JSONObject json = new JSONObject(jsonStr);
 * 2.{}是类  [] 数组  a.get("book")  开头是{ 还是[ 来进行转化
 * 3.从外 入内
 * 自己写的类属性  
	 * 1.字段：HashMap<String,"boolean">  key是字段名字   后边那个是应该是枚举  类,String,Arr即list的类
	 * 2.类名字 
 * 假如生成三个类  第一个  root   root_str  root_str_str
 * 那么按照上面的规矩就是  List<自己写的雷属性> 三个
 * 
 * @author Zone
 *
 */
public class JsonTest {
	
	
	
	public static void main(String[] args) {
		/**
		 * 所需要的参数
		 * 第一个  包名  
		 * 第二个  第一个类的名字
		 * 第三个 保存的文件夹路径
		 */
		String packageName="json.EntityTest";
		String firstClassName="Root";
		String outSavePath="D:/JSONtest";
		
		List<OutPutEntity> outList=new ArrayList<OutPutEntity>();
		File f = new File("D:/json.txt");
//		File f = new File("D:/json2.txt");
//		File f = new File("D:/json3.txt");
//		File f = new File("D:/json5.txt");
		
		String jsonStr = IOUtils.read(f, "gbk");
//		String jsonStr = IOUtils.read(f, "utf-8");
		
		JSONObject json = new JSONObject(jsonStr);
		OutPutEntity classEntity=new OutPutEntity(firstClassName);
		/**
		 * 测试 getType 
		 */
//		System.out.println(getType(json, "store"));
//		System.out.println(getType(json.getJSONObject("store"), "book"));
//		System.out.println(getType(json.getJSONObject("store").getJSONObject("bicycle"), "color"));
//		
		/**
		 * 真正的测试
		 */
		getJsonString(json,classEntity, outList);
		System.out.println("大小："+outList.size());
		for (OutPutEntity item : outList) {
			System.out.println("=====================类名字："+item.className+"=====================");
			for (Entry<String, FieldProperty> aa : item.field.entrySet()) {
				System.out.println("field名："+aa.getKey()+"\t type:____"+aa.getValue());
				if(aa.getValue()!=FieldProperty.STRING&&aa.getValue()!=FieldProperty.ARRAY){
					System.out.print("\t    字段类名字："+item.fieldClassName.get(aa.getKey()));	
				}
				System.out.println();
			}
		}
		writeEntityFile(outList, new File(outSavePath), packageName);
		
	}
	public static String hump(String str){
		String str1=str.substring(0, 1);
		String str2=str.substring(1);
		return  str1.toUpperCase()+str2;
	}
//	==================================写入文件======================================================
	public static void writeEntityFile(List<OutPutEntity> outList,File outFileFolder,String packageName){
		String nextLineStr="\r\n";
		
		for (OutPutEntity item : outList) {
			System.out.println("=====================类名字："+item.className+"=====================");
			//创建java类文件   
			File classFile=new File(outFileFolder, item.className+".java");
			
			boolean haveListUtils=false;
			StringBuilder ksSb=new StringBuilder();
			StringBuilder filedSb=new StringBuilder();
			StringBuilder setGetSb=new StringBuilder();
			String endStr="}";
			for (Entry<String, FieldProperty> aa : item.field.entrySet()) {
				//TODO  在java类文件中写入  各种字段
				System.out.println("field名："+aa.getKey()+"\t type:____"+aa.getValue());
				if(aa.getValue()!=FieldProperty.STRING&&aa.getValue()!=FieldProperty.ARRAY){
					System.out.print("\t    字段类名字："+item.fieldClassName.get(aa.getKey()));	
				}
				String humpStr = hump(aa.getKey());
				String classNameTemp = item.fieldClassName.get(aa.getKey());
				switch (aa.getValue()) {
				case STRING:
					filedSb.append("	private String "+aa.getKey()+";"+nextLineStr);
					
					setGetSb.append("	public String get"+humpStr+"() {"+nextLineStr);
					setGetSb.append("		return "+aa.getKey()+";"+nextLineStr);
					setGetSb.append("	}"+nextLineStr);
					setGetSb.append(nextLineStr);
					setGetSb.append("	public void set"+humpStr+"(String "+aa.getKey()+") {"+nextLineStr);
					setGetSb.append("		this."+aa.getKey()+" = "+aa.getKey()+";"+nextLineStr);
					setGetSb.append("	}"+nextLineStr);
					break;
				case ARRAY:
					haveListUtils=true;
					filedSb.append("	private List<String> "+aa.getKey()+"=new ArrayList<String>();"+nextLineStr);
					
					setGetSb.append("	public List<String> get"+humpStr+"() {"+nextLineStr);
					setGetSb.append("		return "+aa.getKey()+";"+nextLineStr);
					setGetSb.append("	}"+nextLineStr);
					setGetSb.append(nextLineStr);
					setGetSb.append("	public void set"+humpStr+"(List<String> "+aa.getKey()+") {"+nextLineStr);
					setGetSb.append("		this."+aa.getKey()+" = "+aa.getKey()+";"+nextLineStr);
					setGetSb.append("	}"+nextLineStr);
					break;
				case LIST:
					haveListUtils=true;
					filedSb.append("	private List<"+classNameTemp+"> "+aa.getKey()+"=new ArrayList<"+classNameTemp+">();"+nextLineStr);
					
					setGetSb.append("	public List<"+classNameTemp+"> get"+humpStr+"() {"+nextLineStr);
					setGetSb.append("		return "+aa.getKey()+";"+nextLineStr);
					setGetSb.append("	}"+nextLineStr);
					setGetSb.append(nextLineStr);
					setGetSb.append("	public void set"+humpStr+"(List<"+classNameTemp+"> "+aa.getKey()+") {"+nextLineStr);
					setGetSb.append("		this."+aa.getKey()+" = "+aa.getKey()+";"+nextLineStr);
					setGetSb.append("	}"+nextLineStr);
					break;
				case CLASS:
					filedSb.append("	private "+classNameTemp+" "+aa.getKey()+";"+nextLineStr);

					setGetSb.append("	public "+classNameTemp+" get"+humpStr+"() {"+nextLineStr);
					setGetSb.append("	return "+aa.getKey()+";"+nextLineStr);
					setGetSb.append("	}"+nextLineStr);
					setGetSb.append(nextLineStr);
					setGetSb.append("	public void set"+humpStr+"("+classNameTemp+" "+aa.getKey()+") {"+nextLineStr);
					setGetSb.append("	this."+aa.getKey()+" = "+aa.getKey()+";"+nextLineStr);
					setGetSb.append("	}"+nextLineStr);
					break;

				default:
					break;
				}
				setGetSb.append(nextLineStr);
			}
			if(haveListUtils){
				ksSb.append("package "+packageName+";"+nextLineStr);
				//====== 添加导包
				ksSb.append("import java.util.ArrayList;"+nextLineStr);
				ksSb.append("import java.util.List;"+nextLineStr);
				//======
				ksSb.append("public class "+item.className+" {"+nextLineStr);
			}else{
				ksSb.append("package "+packageName+";"+nextLineStr);
				ksSb.append("public class "+item.className+" {"+nextLineStr);
			}
			filedSb.append(nextLineStr);
			String fileWriteStr=ksSb.toString()+filedSb.toString()+setGetSb.toString()+endStr;
			IOUtils.write(classFile, fileWriteStr, "gbk");
		}
	}
	
//	==================================解析======================================================

	public static void addOutPutEntity_NameUnique(OutPutEntity out,List<OutPutEntity> outList){
		for (OutPutEntity item : outList) {
			if(out.className.equals(item.className)){
				int index=outList.indexOf(item);
				outList.set(index, out);
				return;
			}
		}
		outList.add(out);
	}
	
	public enum FieldProperty{
		STRING,CLASS,LIST,ARRAY;
	}
	//验证 JSONObject的 key 的值是 {}是类  [] 数组 还是 字符串的 
	public static FieldProperty getType(JSONObject json,String key){
		String str = json.get(key).toString();
		System.out.println(str);
		if(str.startsWith("{")){
			return FieldProperty.CLASS;
		}
		if(str.startsWith("[{")){
			return FieldProperty.LIST;
		}
		if(str.startsWith("[")){
			//[] 与"[]"的区别  str都是[] 但是类型一个是jsonArray 一个jsonObject所以只能这么做了
			try {
				json.getJSONArray(key);
			} catch (net.sf.json.JSONException e) {
				//如果转换 成数组异常了 就是String 即"[]"
				return FieldProperty.STRING;
			}
			return FieldProperty.ARRAY;
		}
		return FieldProperty.STRING;
	}

	
	public static void getJsonString(JSON json, OutPutEntity classEntity,List<OutPutEntity> outList) {
		if (JSONObject.class.isInstance(json)) {
			JSONObject jsonTemp = (JSONObject) json;
			for (Iterator<String> iter = (jsonTemp).keys(); iter.hasNext();) {
				String key = iter.next();
				switch (getType(jsonTemp, key)) {
				case CLASS:
					classEntity.field.put(key, FieldProperty.CLASS);
					classEntity.fieldClassName.put(key, classEntity.className+"_"+key);
					/** 自调*/
					getJsonString(jsonTemp.getJSONObject(key), new OutPutEntity(classEntity.className+"_"+key), outList);
					break;
				case LIST:
					classEntity.field.put(key, FieldProperty.LIST);
					classEntity.fieldClassName.put(key, classEntity.className+"_"+key);
					/** 自调*/
					getJsonString(jsonTemp.getJSONArray(key), new OutPutEntity(classEntity.className+"_"+key), outList);
					break;
				case STRING:
					classEntity.field.put(key, FieldProperty.STRING);
					break;
				case ARRAY:
					String str = jsonTemp.get(key).toString();
					System.out.println("nimei:"+str);
					classEntity.field.put(key, FieldProperty.ARRAY);
					break;
				default:
					break;
				}
			}
			addOutPutEntity_NameUnique(classEntity, outList);
		}
		if (JSONArray.class.isInstance(json)) {
				JSONArray jsonTemp = (JSONArray) json;
				for (int i = 0; i < jsonTemp.length(); i++) {
					JSONObject jsonObj = jsonTemp.getJSONObject(i);
					/** 自调*/
					getJsonString(jsonObj, classEntity, outList);
				}
		}
	}
	
}
