package fu;

public class Child extends FuK{
	public String name;
	{
		name="a";
	}
	public static void main(String[] args) {
		PrintUtils.printAllField(new Child());
	}
	public class Inner{
		
	}
}
