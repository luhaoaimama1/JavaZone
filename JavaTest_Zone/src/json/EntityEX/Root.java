package json.EntityEX;

import java.util.ArrayList;
import java.util.List;

public class Root {
	/**
	 * 	STRING,CLASS,LIST,ARRAY;
	 */
	//STRING
	private String ab;
	//LIST
	private List<ListEntity> ts=new ArrayList<ListEntity>();
	//CLASS
	private ClassEntity gan;
	//ARRAY
	private List<String> arr=new ArrayList<String>();
	
	public String getAb() {
		return ab;
	}
	public void setAb(String ab) {
		this.ab = ab;
	}
	public List<ListEntity> getTs() {
		return ts;
	}
	public void setTs(List<ListEntity> ts) {
		this.ts = ts;
	}
	public ClassEntity getGan() {
		return gan;
	}
	public void setGan(ClassEntity gan) {
		this.gan = gan;
	}
	public List<String> getArr() {
		return arr;
	}
	public void setArr(List<String> arr) {
		this.arr = arr;
	}
}
