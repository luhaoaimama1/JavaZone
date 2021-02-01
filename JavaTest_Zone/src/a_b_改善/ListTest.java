package a_b_改善;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) throws InterruptedException {
//        List<String> list = new ArrayList<>();
//        list.add(null);
//        list.add(null);
//        list.add(null);
//        System.out.println(list.get(2));


        List<String> Listaaa = new LinkedList<>();
        Listaaa.add("a");
        Listaaa.add("b");
        Listaaa.add("c");
        Thread t = new Thread(new Runnable() {
            int count = -1;

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String e = count++ + "";
                    Listaaa.add(e);
                    System.out.println(e);
                }
            }
        });
        t.start();
       new Thread(new Runnable() {
            @Override
            public void run() {
                for (String s : Listaaa) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("结果："+s);
                }
            }
        }).start();


       Thread.sleep(10000000);
    }
}
