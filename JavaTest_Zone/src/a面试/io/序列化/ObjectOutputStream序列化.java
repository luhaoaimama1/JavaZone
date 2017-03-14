package a面试.io.序列化;

import java.io.*;

/**
 * 示范ObjectOutputStream
 *
 * 当我们使用Serializable接口实现序列化操作的时候，如果一个对象的某一个属性不想
 */
public class ObjectOutputStream序列化 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "/Users/fuzhipeng/Desktop" + File.separator + "test.txt";
        //序列化后 不能直接打开 需要反序列化后 才可以!

        //序列化 写入
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                fileName));
        oos.writeObject(new Person("rollen", 20));
        oos.close();


        //反序列化
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(
                fileName));
        Object obj = input.readObject();
        input.close();
        System.out.println(obj);
    }
}