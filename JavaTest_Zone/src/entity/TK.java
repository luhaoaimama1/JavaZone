package entity;

import fu.PrintUtils;

public class TK {
	String abc = "111";
	String fiel = "111";
	String kb = "111";
	String woca = "111";
	String nimei = "111";
	String haoma = "111";

	public static void main(String[] args) {
		// PrintUtils.printAllField(new TK());
	
		System.out.println(	aa(true));

	}

	private static boolean aa(boolean temp) {
		try {
			if (temp) {
				throw new IllegalAccessException();
			}
			 return true;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			 return false;
		}
	}
}
