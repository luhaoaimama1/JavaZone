package classload;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 对于fianl类 没有接口的测试  。发现不可用！
 */
public class Dynamic {

    //动态代理  通过接口 创建的对象，调用所有的方法 都通过  InvocationHandler的invoke 得到返回值
    public static void main(String[] args) {
        List proxy2 = (List) Proxy.newProxyInstance(List.class.getClassLoader(),
                new Class<?>[]{List.class}, new InvocationHandler() {
                    ArrayList<Object> list = new ArrayList<Object>();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(proxy.getClass().getName());
                        return list.add(args[0]);//return的值如果和 调用的方法返回的不一致 则会抛出异常
                    }
                });
        proxy2.add("a");
    }
}