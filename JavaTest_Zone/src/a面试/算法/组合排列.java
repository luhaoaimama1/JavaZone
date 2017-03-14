package a面试.算法;

import java.util.Arrays;
import java.util.LinkedList;  
import java.util.List;  
  
public class 组合排列 {
  
    public static void main(String[] args) {  
//        String[] array = {"1","2","3","4"};
        String[] array = {"1","2","3"};
        listAll(Arrays.asList(array),"");
    }

    // 思想： 取出一个数 ，剩下的 继续取出 一个数 ，在剩下的继续取出一个数。
    private static void listAll(List candidate,String prefix){  
        System.out.println(prefix);  
        for(int i=0;i<candidate.size();i++){
            List temp = new LinkedList(candidate);
            System.out.println(temp.toString()+"->prefix:"+prefix);
            //取出的数 要组合到一起  即使+=
            listAll(temp,prefix+temp.remove(i));  
        }  
    }



  
} 