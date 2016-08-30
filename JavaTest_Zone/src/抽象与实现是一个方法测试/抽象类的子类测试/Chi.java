package 抽象与实现是一个方法测试.抽象类的子类测试;

/**
 * Created by fuzhipeng on 16/8/30.
 */
public class Chi extends Par {

    @Override
    public void update() {
        b.add("gan");
    }

    public static void main(String[] args) {
        Par chi = new Chi();
        chi.haha();
    }

}
