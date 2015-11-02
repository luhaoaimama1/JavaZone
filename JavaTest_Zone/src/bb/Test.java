package bb;
import aaa.Abc;

public class Test {
	public static void main(String[] args) {
		System.out.println(Abc.class.getName() + "__________"
				+ Abc.class.getSimpleName());
		int a = 0xb0000000;
		System.out.println(a );
		
		Abc b=new Abc();
		b.aa();
	
		System.out.println(	String.format("id:%s\tname:%s\tpic", 1,2));
	}
}
