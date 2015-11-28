package StackTraceElement研究;
public class LineNumberTest {
	public static void main(String args[])
    {
		getLineInfo();
    }
  
    public static void getLineInfo()
    {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        for (StackTraceElement stackTraceElement : ste) {
        	 System.out.println(stackTraceElement);
		}
    }
}