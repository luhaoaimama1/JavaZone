package gson学习与反射.反射clone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parent {
	//TODO 常量测试
	private  final String TAG1="Parent";
	private static final String TAG="Parent";
	
	private String name = "Parent";
	private List<Sun> sunList = new ArrayList<Sun>();
	private Sun sun;
	private Map<String, Sun> sunMap = new HashMap<String, Sun>();
	private EnumTest enumTest = EnumTest.I;
	
	public enum EnumTest {
		I, Love, you;
		private String ab;

		public String getAb() {
			return ab;
		}

		public EnumTest setAb(String ab) {
			this.ab = ab;
			return this;
		}
		
	}

	public String getTAG1() {
		return TAG1;
	}

	public EnumTest getEnumTest() {
		return enumTest;
	}

	public void setEnumTest(EnumTest enumTest) {
		this.enumTest = enumTest;
	}

	public void setSun(Sun sun) {
		this.sun = sun;
	}

	public List<Sun> getSunList() {
		return sunList;
	}

	public void setSunList(List<Sun> sunList) {
		this.sunList = sunList;
	}

	public Map<String, Sun> getSunMap() {
		return sunMap;
	}

	public void setSunMap(Map<String, Sun> sunMap) {
		this.sunMap = sunMap;
	}

	public List<Sun> getSun() {
		return sunList;
	}

	public void setSun(List<Sun> sun) {
		this.sunList = sun;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
