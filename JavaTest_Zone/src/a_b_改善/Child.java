package a_b_改善;

public class Child extends Parent {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Child,onCreate");
    }

    @Override
    public void onDissmis() {
        System.out.println("Child,onDissmis");
        super.onDissmis();
    }

    public static void main(String[] args) {
        new Child().onCreate();
    }

}
