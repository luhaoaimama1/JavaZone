package 红点;
import 红点.lib.UnReadNode;
import 红点.lib.UnReadTree;

/**
 * Created by Zone on 2018/5/13.
 */
public class Test {


    /**
     * UnReadTree简介
     *
     * @param args
     */
    public static void main(String[] args) {
        UnReadTree.configure(
                new UnReadNode(Activity.class).addChilds(
                        new UnReadNode(Activity2_1.class).addChilds(
                                new UnReadNode(Activity3_1.class),
                                new UnReadNode(Activity3_2.class)
                        ),
                        new UnReadNode(Activity2_2.class)
                )
        );
        System.out.println("---------------init-Activity");
        new Activity();
        System.out.println("---------------init-Activity2_1");
        new Activity2_1();
        System.out.println("---------------init-Activity2_2");
        new Activity2_2();
        System.out.println("---------------init-Activity3_1");
        new Activity3_1();
        System.out.println("---------------init-Activity3_2");
        new Activity3_2();

    }
}
