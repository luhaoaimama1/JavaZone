package a面试;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuzhipeng on 2017/3/11.
 */
public class MapForEach测试 {
    public static void main(String[] args) {

        Map<String,String> map=new HashMap<>();
        map.put("kb","1");
        map.put("kb","2");
        map.put("kb","3");
        //map.entrySet() 返回结果是set所以 不重复 并且无序  所以仅仅能打印3
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getValue());
        }
    }

}
