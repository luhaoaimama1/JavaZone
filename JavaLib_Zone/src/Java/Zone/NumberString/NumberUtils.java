package Java.Zone.NumberString;

import java.text.DecimalFormat;

public class NumberUtils {
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
