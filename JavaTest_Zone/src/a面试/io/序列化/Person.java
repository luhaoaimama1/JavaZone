package a面试.io.序列化;

import java.io.*;

/**
 * 实现具有序列化能力的类
 * */
public class Person implements Serializable {
    public Person(){
     }
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString(){
        return "姓名：" +name + "  年龄：" +age;
    }
    private String name;
    private int age;
}