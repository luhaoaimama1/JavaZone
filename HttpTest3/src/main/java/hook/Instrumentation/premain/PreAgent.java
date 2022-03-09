package hook.Instrumentation.premain;
 
import java.lang.instrument.Instrumentation;
 
public class PreAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation)  {

        /*转换发生在 premain 函数执行之后，main 函数执行之前，这时每装载一个类，transform 方法就会执行一次，看看是否需要转换，
        所以，在 transform（Transformer 类中）方法中，程序用 className.equals("TransClass") 来判断当前的类是否需要转换。*/
        // 方式一：
        System.out.println("我是两个参数的 Java Agent premain");
        instrumentation.addTransformer(new TestTransformer());
    }
    public static void premain(String agentArgs){
        System.out.println("我是一个参数的 Java Agent premain");
    }
}