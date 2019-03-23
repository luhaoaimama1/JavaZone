package a_新手;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2017/3/18.
 */
public class Test4 {

    public Test4() {

    }

    public static void main(String[] args) {
        Object a= null;
        if(a instanceof  Test4){
            System.out.println("属于");
        }else{
            System.out.println("不属于");
        }
    }
}
