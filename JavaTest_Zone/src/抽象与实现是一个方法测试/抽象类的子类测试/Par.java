package 抽象与实现是一个方法测试.抽象类的子类测试;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 16/8/30.
 */
public abstract class Par {
   List<String> b=new ArrayList<>();
    public void haha(){
        update();
        System.out.println(b);
    }

    protected  void update(){};
}
