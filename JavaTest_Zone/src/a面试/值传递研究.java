package a面试;

/**
 * Created by fuzhipeng on 2017/3/11.
 */
public class 值传递研究 {
    public static void main(String[] args) {

        Entity e = new Entity();
        change(e);//入栈  出栈
        //这里和方法没有关系   如果发生 值的改变也是   栈帧中 找到了 对象 修改了值 。然后出栈后也用到了对象。
        System.out.println(e.a);
    }

    private static void change(Entity e) {
        e=new Entity();
        e.a=10;
    }

}

class Entity {
    public int a = 1;
}