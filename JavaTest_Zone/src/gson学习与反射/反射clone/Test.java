package gson学习与反射.反射clone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gson学习与反射.反射clone.Parent.EnumTest;
import gson学习与反射.反射clone.utils.ReflectUtils;

public class Test {
	public static void main(String[] args) {
//		String abc="ab";
//		String bc=abc;
//		abc="bc";
//		System.out.println("abc:"+abc);
//		System.out.println("bc:"+bc);
		//上面 证明 基本类型  不克隆 赋值修改也没事
		
		Child child = new Child();
		child.setStr("child");
		Parent parent = new Parent();
		parent.setSun(new Sun());
		parent.setEnumTest(EnumTest.Love.setAb("love"));
		List<Sun> sunList = new ArrayList<Sun>();
		for (int i = 0; i < 3; i++) {
			Sun ss = new Sun();
			ss.setName("Sun"+i);
			sunList.add(ss);
		}
		parent.setSunList(sunList);
		Map<String, Sun> sunMap = new HashMap<String, Sun>();
		for (int i = 0; i < 3; i++) {
			Sun ss = new Sun();
			ss.setName("Sun"+i);
			sunMap.put("Sun"+i, ss);
		}
		parent.setSunMap(sunMap);
		
		child.setParent(parent);
		
		Child childClone = ReflectUtils.clone(child);
		Sun ss = new Sun();
		ss.setName("Sun"+"浪子");
		childClone.getParent().getSunList().add(ss);
		childClone.getParent().getSunMap().put("Sun"+"浪子", ss);
		childClone.getParent().setEnumTest(EnumTest.you);
		System.out.println("-----------child---------------------------");
		print(child);
		System.out.println("-----------childClone--------------------");
		print(childClone);
		
		//map 测试
		Map<String,Sun> map=new HashMap<String,Sun>();
		map.put("gaga", new Sun().setName("Sun"+111));
		map.put("ex0hei1", new Sun().setName("Sun"+222));
		map.put("fengle",  new Sun().setName("Sun"+333));
		Map<String, Sun> mapClone = ReflectUtils.clone(map);
		mapClone.put("Zone",new Sun().setName("Sun"+444));
		System.out.println("-----------map---------------------------");
		print(map);
		System.out.println("-----------mapClone---------------------------");
		print(mapClone);
		//list测试
		List<Sun> list=new ArrayList<Sun>();
		list.add( new Sun().setName("Sun"+111));
		list.add( new Sun().setName("Sun"+222));
		list.add( new Sun().setName("Sun"+333));
		List<Sun> listClone = ReflectUtils.clone(list,true);
		listClone.add( new Sun().setName("Sun"+444));
		System.out.println("-----------list---------------------------");
		print(list);
		System.out.println("-----------listClone---------------------------");
		print(listClone);
		
	}
	private static void print(List<Sun> list1) {
		for (Sun sun : list1) {
			System.out.println("value:"+sun.getName());
		}
	}
	private static void print(Map<String, Sun> map) {
		for ( Entry<String, Sun> item : map.entrySet()) {
			System.out.println("key:"+item.getKey()+"\t value:"+item.getValue().getName());
		}
		
	}
	public static void print(Child child){
		System.out.println("TAG:"+child.getParent().getTAG1());
		List<Sun> sunListPrint = child.getParent().getSunList();
		System.out.println("list");
		for (Sun sun : sunListPrint) {
			System.out.println(sun.getName());
		}
		Map<String, Sun> map = child.getParent().getSunMap();
		for ( Entry<String, Sun> item : map.entrySet()) {
			System.out.println("key:"+item.getKey()+"\t value:"+item.getValue().getName());
		}
		System.out.println("枚举类"+child.getParent().getEnumTest()+"\t 内部的值："+child.getParent().getEnumTest().getAb());
	}
}
