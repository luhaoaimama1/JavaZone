package 父类相关;

/**
 * Created by fuzhipeng on 2016/12/1.
 */
public class Main extends Par_ {
    public static void main(String[] args) {
        new Main().heihei();
    }

    @Override
    public void heihei() {
        super.heihei();
        System.out.println("gaga");
    }
}
