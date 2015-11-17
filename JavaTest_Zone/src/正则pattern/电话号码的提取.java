package 正则pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 电话号码的提取 {
	
	// 11位 18510640011 185-1064-0011
	// 10位 4002342222 400-234-2222
	// 8位 22223334 2222-3334
	public static void main(String[] args) {
		String str = "wo185106400111we18510640011ishenmald185-1064-0011klajldj"
				+ "185-1064-0011fadfjladjkfl4002342222akjdfajdflkajdfl400-234-2222ajdflka22223334jld"
				+ "fjl2222-3334ajd 2222-33344aa133-7015-6232";
		RexUtils ru=new RexUtils();
		ru.addRule("\\D(\\d{11})\\D");
		ru.addRule("\\D(\\d{3}-\\d{4}-\\d{4})\\D");
		ru.addRule("\\D(\\d{10})\\D");
		ru.addRule("\\D(\\d{2}-{1}\\d{4}-\\d{4})\\D");
		ru.addRule("\\D(\\d{8})\\D");
		ru.addRule("\\D(\\d{4}-{1}\\d{4})\\D");
//		getValuePhone(str,ru.build());
		getValue(str,ru.build());
	}

	public static class RexUtils{
		private  StringBuffer sb=new StringBuffer();
		List<String> rules=new ArrayList<String>();
		private boolean isFirst=true;
		public RexUtils addRule(String rex){
			rules.add(rex);
			if (isFirst) {
				sb.append(rex);
				isFirst=false;
			}else{
				sb.append("|" +rex);
			}
			return this;
		}
		public String build() {
			return sb.toString();
		}
	}
	
	/**
	 * 得一正则表达对应的内容
	 * 
	 * @param con
	 * @param reg
	 * @return
	 */
	private static void getValue(String con, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(" "+con+" ");//因为这里为了让在开头的、结尾的也匹配上 所以得到的顺序-1
		String res = "";
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				res = m.group(i);
				if(res == null||i==0){
					continue;
				}
				//所以这里顺序-1
				System.out.println(res+"-----------\t ks:"+(m.start(i)-1)+"   \tend:"+(m.end(i)-1)+"\t i："+i);
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
	/**
	 * 得一正则表达对应的内容
	 * 
	 * @param con
	 * @param reg
	 * @return
	 */
	private static void getValuePhone(String con, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(con);
		String res = "";
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				res = m.group(i);
				if (beforeIsNum(con, m.start(i))||afterIsNum(con, m.end(i))) {
				}else{
					System.out.println(res+"-----------\t ks:"+m.start(i)+"   \tend:"+m.end(i));
				}
			}
		}
	}
	private static boolean  beforeIsNum(String str,int index){
		if(index==0){
			return false;
		}
		try {
			char a = str.charAt(index-1);
			Integer.parseInt(String.valueOf(a));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	private static boolean  afterIsNum(String str,int index){
		if(index>=str.length()){
			return false;
		}
		try {
			char a = str.charAt(index);
			Integer.parseInt(String.valueOf(a));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
