package classload;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 对于fianl类 没有接口的测试  。发现不可用！
 */
public class Dynamic implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = method.invoke(proxy, args);
        return result;
    }

    public static void main(String[] args) {
        StringBuffer proxy = (StringBuffer) Proxy.newProxyInstance(String.class.getClassLoader(),
                new Class<?>[]{StringBuffer.class}, new Dynamic());
        proxy.append("a");

    }
}