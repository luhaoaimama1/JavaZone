package instacne;

public class Test {
	public static void main(String[] args) {
		Child c=new Child();
		Par p=new Par();
		No n=new No();
		if(Par.class.isAssignableFrom(Child.class)){
			System.out.println("Par     是相等");
		}else{
			System.err.println("a");
		}
		if(Child.class.isAssignableFrom(No.class)){
			System.out.println("NO    是相等");
		}
	}
}
