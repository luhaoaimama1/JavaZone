package a未分类;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by fuzhipeng on 2016/12/12.
 */
public class Person_Equals_Hashcode {
    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new Student(1, "zhangsan"));
        hs.add(new Student(2, "lisi"));
        hs.add(new Student(3, "wangwu"));
        hs.add(new Student(1, "zhangsan"));
        Iterator it = hs.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static class Student {
        int num;
        String name;

        Student(int num, String name) {
            this.num = num;
            this.name = name;
        }

        public String toString() {
            return num + ":" + name;
        }

// todo 注释:
// 因 为在根据hashcode()对两次建立的new Student(1,"zhangsan")对象进行比较时，
// 生成的是不同的哈希码值，所以hashset把他当作不同的对象对待了，
// 当然此时的 equals()方法返回的值也不等（这个不用解释了吧）
// todo 不注释:
//        即便两次调用了new Student(1,"zhangsan")，
//        我们在获得对象的哈希码时，
//        根据重写的方法hashcode()，
//        获得的哈希码肯定是一样的（这一点应该没有疑问吧）。
        public int hashCode() {
            return num * name.hashCode();
        }

        public boolean equals(Object o) {
            Student s = (Student) o;
            return num == s.num && name.equals(s.name);
        }
    }
}
