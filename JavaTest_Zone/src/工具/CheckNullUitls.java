package 工具;

/**
 * Created by fuzhipeng on 2018/6/12.
 */
public class CheckNullUitls {
    static int circleNums = Integer.MAX_VALUE;

    public static void main(String[] args) {
        A a = new A();
        //** 在方法内 执行的时候 检测 但是return不能用 Obj... 所以只能这样写
//        System.out.println(checkSafe(() -> quickArr(a)));
        long now = System.currentTimeMillis();
        for (int i = 0; i < circleNums; i++) {
            checkSafe(() -> quickArr(a.b.c, a.m.a));
        }
        long end = System.currentTimeMillis();
        System.out.println("差值：" + (end - now));

        long now2 = System.currentTimeMillis();
        for (int i = 0; i < circleNums; i++) {
            if(a!=null&&a.b!=null&&a.b.c!=null&&a.m!=null){
                quickArr2();
            }
        }
        long end2 = System.currentTimeMillis();
        System.out.println("差值2：" + (end2 - now2));

    }
    public static void  quickArr2(){
        quickArr3();
    }
    public static void  quickArr3(){
        Integer[] a = new Integer[]{1, 2};
    }

    public static class A {
        public B b;
        public M m;
        public int a;
    }

    public static class B {
        public B c;
        public int a;
    }

    public static class M {
        public int a;
    }

    public static class C {
        public int a;
    }

    public static boolean checkSafe(Callback callback) {
        try {
            callback.checkNull();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public interface Callback {
        void checkNull();
    }

    public static Object[] quickArr(Object... var) {
        return var;
    }

}
