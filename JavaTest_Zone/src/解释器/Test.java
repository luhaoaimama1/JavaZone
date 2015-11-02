package 解释器;

//测试程序，计算 a-b+2

public class Test {
	private static Expression ex;
	private static Context con;

	public static void main(String[] args) {
		//这里是用来保存变量的  因为解释的时候  从map取出来参数
		con = new Context();
		// 设置变量、常量
		Variable a = new Variable();
		Variable b = new Variable();
		Constant c = new Constant(2);
		// 为变量赋值
		con.addValue(a, 10);
		con.addValue(b, 7);
		// 运算，对句子的结构由我们自己来分析，构造
		ex =new Add(new Subtract(a, b), c);//这里其实就是我们真正要做的  *****
		System.out.println("运算结果为：" + ex.interpret(con));//interpret:解释
	}
}
//http://www.zhujiangroad.com/program/JAVA/2447.html    这个代码的地址