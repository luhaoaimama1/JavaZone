package a面试;

/**
 * Created by fuzhipeng on 2017/3/11.
 */
public class Finally测试 {
    public static void main(String[] args) {
        kb();
        System.out.println("完成");
    }

    private static void kb() {
        try {
            System.out.println("hehe");
            return;//函数在 return后会销毁   所以在return 前执行finally
        } catch (Exception e) {

        } finally {
            System.out.println("finally");
        }
    }
}
