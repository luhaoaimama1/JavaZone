package a_b_改善;


import com.google.gson.annotations.SerializedName;
import gson学习与反射.gson.gsonList等测试.GsonUtils;

public class TabA {

    class BCA{
        @SerializedName("is_new")
        String is_new="";
        @SerializedName("data")
        Object data;
    }

    public static void main(String[] args) {
        String b="{\n" +
                " \n" +
                "        \"is_new\": 1,\n" +
                "        \"data\": {\n" +
                "            \"video_task\": {\n" +
                "                \"ad_group_id\": 1,\n" +
                "                \"param_ext\": \"{\\\"uid\\\":\\\"5388154\\\",\\\"task_type\\\":\\\"my_welfare_sign_video_task\\\"}\"\n" +
                "            }           \n" +
                "        }\n" +
                "}\n" +
                "       ";
        GsonUtils.fromJson(b,BCA.class);
        System.out.println();
    }
}
