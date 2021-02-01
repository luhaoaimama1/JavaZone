package a_新手;


import gson学习与反射.gson.gsonList等测试.GsonUtils;

public class MAAAA {

    static class TestA{
        String emptyToInt;
        String strToInt;
        int intToStr;
//        int zfToBoolean;
    }

    static class TestB{
        int emptyToInt;
        int strToInt;
        String intToStr;
//        boolean zfToBoolean;
    }



    public static void main(String[] args) {
        TestA testA = new TestA();
        testA.emptyToInt="";
        testA.strToInt="123";
        testA.intToStr=123;
//        testA.zfToBoolean=1;


        String str = GsonUtils.toJson(testA);
        System.out.println(str);
        TestB testB = GsonUtils.fromJson(str, TestB.class);
        System.out.println(testB.toString());
    }
}
