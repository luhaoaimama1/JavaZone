package 算法.算法书籍;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class 绘图 {
    public static void main(String[] args) {
        int N = 100;
//        StdDraw.setXscale(0, N);
//        StdDraw.setYscale(0, N * N);
//        StdDraw.setPenRadius(.01);
//        for (int i = 1; i < N; i++) {
//            StdDraw.point(i, i);
//            StdDraw.point(i, i * i);
//            StdDraw.point(i, i * Math.log(i));
//        }

//        StdDraw.circle(0);
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(Color.RED);
        StdDraw.circle(100,100,50);
    }
}
