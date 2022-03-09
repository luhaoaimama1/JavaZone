package a_新手;


import gson学习与反射.gson.gsonList等测试.GsonUtils;

public class MAAAA2 {

    static class TestA {
        String a;
        boolean intToStr = true;
    }

    static class TestB {
        String a;
    }


    public static void main(String[] args) {
        TestB testB = new TestB();
        testB.a = "123";

        String str = GsonUtils.toJson(testB);
        System.out.println(str);
        TestA testA = GsonUtils.fromJson(str, TestA.class);
        System.out.println(testA.toString());
    }
}
