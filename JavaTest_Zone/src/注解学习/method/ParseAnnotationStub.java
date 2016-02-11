package 注解学习.method;  
  
import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
  
public class ParseAnnotationStub {  
      
    //包装了下Java基本的方法反射（范围是带了我们特定注解的方法）  
    //传入我们要执行的类型，所以我们时常发现某些框架要我们定义好类查找的范围，或前后缀什么的  
    //可以设置返回值为空void 或者Object通用，这里我们为了测试采用String返回值  
    public static String parseMethod(Class<?> clazz) throws IllegalArgumentException,  
            IllegalAccessException, InvocationTargetException,  
            SecurityException, NoSuchMethodException, InstantiationException {  
          
        //变量该对象的方法  
        for (Method method : clazz.getDeclaredMethods()) {  
              
            //获取方法的注解，这里特定获取方法上@HelloWorld注解  
            HelloWorldAnnotation say = method.getAnnotation(HelloWorldAnnotation.class);  
            //如果@HelloWorld注解不空，即方法有@HelloWorld注解  
            if (say != null) {  
                //这里我们先前定义了   UseHelloWorld.sayHello(String name)方法  
                //这里可以从注解中获取值，或者直接运行，或者缓存该对象方法  
                String name = say.name();  
                return (String)method.invoke(clazz.newInstance(), name);  
            }  
              
        }  
        return "";  
    }  
}  