package hook.Instrumentation.premain;
 
import java.lang.instrument.Instrumentation;

class AgentRunning {
    public static void agentmain(String agentArgs, Instrumentation inst){
        System.out.println("==========AgentRunning doing==========");
        inst.addTransformer(new TestTransformer());
        inst.addTransformer(new ByteChangeTransformerKt());
        System.out.println("==========AgentRunning after==========");
    }
}