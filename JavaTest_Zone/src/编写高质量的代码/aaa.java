package 编写高质量的代码;

public class aaa {
	public enum Season{
		SPRING(1),SUMMER(2),WINNER(3);
		private Season(int a) {
			// TODO Auto-generated constructor stub
		}
	}
	/**
	 * TODO a
	 * @param args
	 */
	public static void main(String[] args) {
		Season a=Season.WINNER;
		switch (a) {
		case SPRING:
			break;
		case SUMMER:
			break;
		}
		System.out.println(1);
	}
}
