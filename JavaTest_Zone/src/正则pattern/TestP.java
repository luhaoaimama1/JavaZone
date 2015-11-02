package 正则pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 即重字符串''中取出 _abc_ abc
 * 
 * @author Zone
 * 
 */
public class TestP {

	public static void main(String[] args) {
		tp("abd _kam_ ajda _dal_ dada _abab_");
//		tp("abd__");
	}

	/**
	 * 即重字符串''中取出 _abc_  并替换成 abc  
	 */
	public static void tp(String abc) {
		Pattern pa = Pattern.compile("_.*?_");
		Matcher m = pa.matcher(abc);
		String tempStr=abc;
		while (m.find()) {
			String temp = m.group();
			String va = temp.substring(1, temp.length() - 1);
			tempStr=tempStr.replace(temp, va);
		}
		System.out.println(tempStr);
	}
}