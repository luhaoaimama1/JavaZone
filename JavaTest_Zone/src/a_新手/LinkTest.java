package a_新手;

import java.util.HashMap;
import java.util.Map;

public class LinkTest {
    public static void main(String[] args) {

        String s = "kuaiyin://sdk?name=chinese_reader&params={\"feed_ad_id\":\"945932782\",\"reward_ad_id\":\"945932759\"}";

        System.out.println("===>"+s.startsWith("kuaiyin://sdk"+"?"));

        String[] split = s.split("/sdk\\?");

//        System.out.println("0->"+split[0]);
//        System.out.println("1->"+split[1]);
//
//        String[] content=split[1].split("\\&");
//        System.out.println("content0->"+content[0]);
//        System.out.println("content1->"+content[1]);
//
//        content[0].split("\\=");
//        System.out.println("content0->"+content[0]);
//        System.out.println("content1->"+content[1]);
        System.out.println("/");

        HashMap<String, String> stringStringHashMap = exractQueryParams(s, "/sdk");
        for (Map.Entry<String, String> stringStringEntry : stringStringHashMap.entrySet()) {
            System.out.println("key->"+stringStringEntry.getKey()+"\t"+"value->"+stringStringEntry.getValue());
        }
    }


    public static HashMap<String,String> exractQueryParams(String url,String path){
        String[] split = url.split(path + "\\?");
        HashMap<String, String> map = null;
        if(split[1]!=null){
            String[] split1 = split[1].split("&");
            map = new HashMap<>(split1.length);
            for (String s : split1) {
                String[] keyValue = s.split("=");
                map.put(keyValue[0], keyValue[1]);
            }
        }
        return map;
    }
}
