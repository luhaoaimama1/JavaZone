package a面试;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2017/3/14.
 * 注意：
 * 1.是浅拷贝；对于基本数据则是深
 * 2.必须是数组 不能是 集合。
 */
public class SystemCopy测试 {
    public static void main(String[] args) {
//
        Copy[] copies = new Copy[]{
                new Copy(0),
                new Copy(1),
                new Copy(2),
                new Copy(3),
        };
        Copy[] copies2 = new Copy[4];

        //浅拷贝
        System.arraycopy(copies, 0, copies2, 1, 2);

        System.out.println(1);


    }

    static class Copy {
        public Copy(int i) {
            this.i = i;
        }

        public int i = 0;
    }
}
