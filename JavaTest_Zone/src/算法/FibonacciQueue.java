package 算法;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2018/4/27.
 * 斐波那契数列（Fibonacci sequence），又称黄金分割数列

 在数学上，斐波纳契数列以如下被以递归的方法定义：F（0）=0，F（1）=1，F（n）=F(n-1)+F(n-2)（n≥2，n∈N*）

 指的是这样一个数列：0、1、1、2、3、5、8、13、21、34、……

 作者：eddy_wiki
 链接：https://www.jianshu.com/p/76f87524f855
 來源：简书
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class FibonacciQueue {

    public static void main(String[] args) {
        int n = 10;

        //这样的好处  第二次算的时候 直接就能取结果
        List<Integer> caches = new ArrayList<>();
        if (caches.size() == 0) {
            caches.add(0);
            caches.add(1);
        }

        for (int i = 2; i <= n; i++) {
            //caches.size() 数组越界的问题
            if (caches.size() < i + 1 || caches.get(i) == null)
                caches.add(caches.get(i - 2) + caches.get(i - 1));
            else
                continue;
        }

        System.out.println(caches.get(n));

    }
}
