package print游戏;

import java.io.IOException;

public class ExcpetionPrintLine {
	  public static void quiet(Exception e){
//        LogUtils.e(callMethodAndLine()+"\t Message:"+e.getMessage()+" \t cause:"+e.getCause());
        System.out.println(callMethodAndLine()+"\t Message:"+e.getMessage()+" \t cause:"+e.getCause());
    }

    /**
     * log这个方法就可以显示超链
     */
    private static String callMethodAndLine() {
        String result = "line at ";
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result += thisMethodStack.getClassName()+ ".";
        result += thisMethodStack.getMethodName();
        result += "(" + thisMethodStack.getFileName();
        result += ":" + thisMethodStack.getLineNumber() + ")  ";
        return result;
    }

    public static void main(String[] args) {
        quiet(new IOException("heihei"));
    }
}
