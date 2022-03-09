
package hook.Instrumentation.premain;
import com.sun.tools.attach.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class TestAgent {
 
    public static void main(String[] args) {
//        doing();//如果在代理前就实例化了 那么方法没法改变啊

        try {
//            -javaagent:/Users/fuzhipeng/JavaProjects/JavaZone/output/artifacts/zonetest_jar/zonetest.jar
            //1.动态加载agent
            // 获取当前jvm的进程pid
            String pid = ManagementFactory.getRuntimeMXBean().getName();
            int indexOf = pid.indexOf('@');
            if (indexOf > 0) {
                pid = pid.substring(0, indexOf);
            }
            System.out.println("now JVM Process ID: " + pid);
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent("/Users/fuzhipeng/JavaProjects/JavaZone/output/artifacts/zonetest_jar/hook.jar");
            // 获取当前jvm

//            for (VirtualMachineDescriptor virtualMachineDescriptor : VirtualMachine.list()) {
//                VirtualMachine attach = VirtualMachine.attach(virtualMachineDescriptor);
////                if(attach.)
//                attach.loadAgent("/Users/fuzhipeng/JavaProjects/JavaZone/output/artifacts/zonetest_jar/zonetest.jar");
//            }
            // 当前jvm加载代理jar包,参数1是jar包路径地址,参数2是给jar包代理类传递的参数

        } catch (AttachNotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AgentLoadException e) {
            e.printStackTrace();
        } catch (AgentInitializationException e) {
            e.printStackTrace();
        }

        //2. 执行任意方法

        doing();
    }

    private static void doing() {
        new Test2().toast();
        new TestAgent().test();
    }

    public void test() {
        System.out.println("I'm TestAgent");
    }
//
}