package print游戏;

public class Split {
	public static void main(String[] args) {
		String ab="addd::ba";
		for (String string : ab.split("::")) {
			System.out.println(string);
		}
	}

}
