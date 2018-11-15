package a_新手;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2018/11/2.
 */


public class ListAddTest {
    public static void main(String[] args) {
        List<Person> list1=new ArrayList<>();
        list1.add(new Person("我是第一"));
        List<Person> list2=new ArrayList<>();
        list2.addAll(list1);
        list2.get(0).name="?";
        System.out.println(list1.get(0).name);
        System.out.println(list2.get(0).name);
    }

    public static class Person {
       public String name = "abc";

        public Person(String name) {
            this.name = name;
        }
    }
}
