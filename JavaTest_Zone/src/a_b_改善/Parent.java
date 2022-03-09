package a_b_改善;

public class Parent {
    public void onCreate(){
        System.out.println("Parent,onCreate");
        onDissmis();
    }

    public void onDissmis(){
        System.out.println("Parent,onDissmis");
    }
}
