package 解释器;

//非终结符
public class Add extends Expression {
	private Expression left, right;

	public Add(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public int interpret(Context con) {
		return left.interpret(con) + right.interpret(con);
	}
}
