package a面试;

/**
 * Created by fuzhipeng on 2017/3/11.
 */
public class 静态方法没有覆盖 {
    public static void main(String[] args) {

        new Father().gan();
        new Child().gan();
    }
    static class Father{
        public static void gan(){
            System.out.println("Father");
        }
    }
    static class Child extends Father{
        public static void gan(){
            System.out.println("Child");
        }
    }
}
