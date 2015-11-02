package 解释器;

//抽象表达式角色，也可以用接口来实现

abstract class Expression {
//	解释
	public abstract int interpret(Context con);
}