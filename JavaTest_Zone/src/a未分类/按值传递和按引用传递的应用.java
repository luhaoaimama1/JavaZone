package a未分类;

import java.util.ArrayList;
import java.util.List;
/**
 * 值传递仅仅传递的是值引用传递,传递的是内存地址(即原来那个引用的地址)
 * 
 * 按引用传递  传递的是新的引用
 * @author Zone
 *
 */

public class 按值传递和按引用传递的应用 {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		String abc=new String("123");
		list.add(abc);
		String kb=abc;//kb->"123"  abc->"123"  这种赋值 相当于给地址123多出一种引用
		
		abc=tihua(abc);//必须添加返回值 然后赋给abc这样才能达到修改abc的目的！！！
		System.out.println(abc);
		
		
		tihua(list);
		for (String string : list) {
			System.out.println("ri___"+string);
		}
		String kan="123";
		Ceshi ceshi = new Ceshi();
		ceshi.add(kan);
		ceshi.remove(kan);
		System.out.println("kan:"+kan);
	}
	private static void tihua(List<String> list1) {
		for (String item : list1) {
			//item=null;//是错的 因为这也是多出来的引用 而不是直接更改 地址 
			list1.set(list1.indexOf(item), null);
		}
		
	}
	/**
	 * 这种方法调用  abc是另一个变量 即另一个引用 所以改变这个abc不会改变 原来的abc
	 * 看起来是一个引用 其实不然 因为{} 不是一个作用区间所以名字一样也无所谓
	 * @param abc
	 * @return
	 */
	private static String tihua(String abc) {
		abc=null;
		return abc;
	}
	static class Ceshi{
		List<String> list=new ArrayList<String>();
		public void add(String e) {
			list.add(e);
		}
		public void  remove(String e){
			list.set(list.indexOf(e), null);
		}
	} 
}
