package 解释器;

//非终结符
class Subtract extends Expression {
	private Expression left, right;

	public Subtract(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public int interpret(Context con) {
		return left.interpret(con) - right.interpret(con);
	}

}
