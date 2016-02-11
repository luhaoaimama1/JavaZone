package 注解学习.method;  
  
public class HelloWorldStub {  
  
    @HelloWorldAnnotation(name = "小明")  
    public String sayHello(String name) {  
        if (name == null ) {  
            name = "";  
        }         
        return name + " say hello world!";  
    }  
}  