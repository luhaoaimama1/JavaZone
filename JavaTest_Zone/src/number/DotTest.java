package number;

import java.text.DecimalFormat;
/**
	阅读量1000以上计数显示为1k
	eg：2948=2.9k
	        23400=23k
	        123456=123k
	阅读量1000000（100万）以上计数显示为1m
	eg：1230000=1.2m
	        12300000=12m
	阅读量100000000（10亿）以上计数显示为1b
	eg：323000000=3.2b
	以此类推……       
 * @author Zone
 *
 */
public class DotTest {
	public static void main(String[] args) {
		String a = "1111";
		a = "";
		System.out.println(numberShow(a));
	}

	public static String numberShow(String numberStr) {
		if ("".equals(numberStr)) {
			return numberStr;
		}
		long number = 0;
		try {
			number = Long.parseLong(numberStr.trim());
			double temp = 0.0;
			// 阅读量100000000（10亿）以上计数显示为1b
			if (number >=( temp = Math.pow(10, 8))) {
				double tempNum = number / temp;
				if(tempNum<10){
					return numberDotFormat(tempNum, 1) + "b";	
				}else{
					return numberDotFormat(tempNum, 0) + "b";	
				}
			}
			// 阅读量1000000（100万）以上计数显示为1m
			if (number >= (temp = Math.pow(10, 6))) {
				double tempNum = number / temp;
				if(tempNum<10){
					return numberDotFormat(tempNum, 1) + "m";	
				}else{
					return numberDotFormat(tempNum, 0) + "m";	
				}
			}
			// 阅读量1000以上计数显示为1k
			if (number >= (temp = Math.pow(10, 3))) {
				double tempNum = number / temp;
				if(tempNum<10){
					return numberDotFormat(tempNum, 1) + "k";	
				}else{
					return numberDotFormat(tempNum, 0) + "k";	
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return numberStr;
		}
		return numberStr;
	}

	/**
	 * 
	 * @param number
	 * @param dotNumber  例如1.1111 当0显示1  3显示1.111   10则显示1.1111 即超出没事的
	 * @return
	 */
	public static String numberDotFormat(double number,int dotNumber){
		StringBuilder sb=new StringBuilder();
		if(dotNumber==0){
			sb.append("#");
		}else{
			sb.append("#.");
			for (int i = 0; i < dotNumber; i++) {
				sb.append("#");
			}
		}
		DecimalFormat   df=new  DecimalFormat(sb.toString());  
		return df.format(number);
	}
}
