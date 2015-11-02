package 异常学习;

import java.util.ArrayList;
import java.util.List;


public class TEst {
	private List<Integer> list;
	{
		list=new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
	}
	public static void main(String[] args) {
		TEst a = new TEst();
		a.list.subList(5, 95).clear();
		for (Integer item : a.list) {
			System.out.print(item+"\t");
		}
		System.out.println();
		a.list.subList(0,5).add(1000);
		for (Integer item : a.list) {
			System.out.print(item+"\t");
		}
		System.out.println();
		a.list.add(100);
		for (Integer item : a.list) {
			System.out.print(item+"\t");
		}
		System.out.println();
		a.list.subList(0,5).set(0, -1);
		for (Integer item : a.list) {
			System.out.print(item+"\t");
		}
		System.out.println("aa");
		List<Integer> b = a.list.subList(0,5);
		b.clear();
		for (Integer item : b) {
			System.out.print(item+"\t");
		}
		System.out.println("pp");
		for (Integer item : a.list) {
			System.out.print(item+"\t");
		}
	}

}
