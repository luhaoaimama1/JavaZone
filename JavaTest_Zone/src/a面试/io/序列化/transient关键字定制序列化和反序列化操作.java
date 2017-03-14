package a面试.io.序列化;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
  
/**
 * 序列化和反序列化的操作
 *
 * 注意：Serializable接口实现的操作其实是吧一个对象中的全部属性进行序列化，
 * 当然也可以使用我们上使用是Externalizable接口以实现部分属性的序列化，但是这样的操作比较麻烦
 * 当我们使用Serializable接口实现序列化操作的时候，如果一个对象的某一个属性不想被序列化保存下来，
 * 那么我们可以使用transient关键字进行说明：
 * */
public class transient关键字定制序列化和反序列化操作 {
    public static void main(String[] args) throws Exception{
        ser(); // 序列化
        dser(); // 反序列话
    }
  
    public static void ser()throws Exception{
        File file = new File("d:" + File.separator + "hello.txt");
        ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(
                file));
        out.writeObject(new Person1("rollen", 20));
        out.close();
    }
  
    public static void dser()throws Exception{
        File file = new File("d:" + File.separator + "hello.txt");
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(
                file));
        Object obj =input.readObject();
        input.close();
       System.out.println(obj);
    }
}
  
class Person1 implements Serializable{
    public Person1(){
  
    }
  
    public Person1(String name, int age){
        this.name = name;
        this.age = age;
    }
  
    @Override
    public String toString(){
        return "姓名：" +name + "  年龄：" +age;
    }
  
    // 注意这里
    private transient String name;
    private int age;
}