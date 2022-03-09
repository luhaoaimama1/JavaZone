package java8新特性;

public class TestJava8 {
    static class DefaultInterImpl implements DefaultInter {
        public boolean isAutoWindow;

        @Override
        public boolean isAuto() {
            return isAutoWindow;
        }
    }

    public static void main(String[] args) {
        DefaultInterImpl defaultInter = new DefaultInterImpl();
        defaultInter.isAutoWindow=true;
        DefaultInterImpl defaultInter2 = new DefaultInterImpl();
        System.out.println("defaultInter__isAuto:"+defaultInter.isAuto());
        System.out.println("defaultInter2__isAuto:"+defaultInter2.isAuto());
    }
}
