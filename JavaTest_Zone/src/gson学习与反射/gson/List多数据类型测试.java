package gson学习与反射.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import gson学习与反射.gson.gsonList等测试.GsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2018/4/20.
 */
public class List多数据类型测试 {


    public static void main(String[] args) {


//        Contains contains = new Contains();
//        List<ContainsItem> list = contains.getList();
//        for (int i = 0; i < 2; i++) {
//            list.add(new ContainsItem(1, new DataA("title" + i)));
//        }
//        for (int i = 0; i < 2; i++) {
//            list.add(new ContainsItem(2, new DataB("luck" + i)));
//        }
//
//        String jsonStr = null;
//        System.out.println("data:" + (jsonStr = GsonUtils.toJson(contains)));


        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse("{\"title\":\"title?AA\"}"); // 将json字符串转换成JsonElement
        ContainsItem item = new ContainsItem(1, jsonElement);

        System.out.println("生成：" + ((DataA) item.getBeans()).title);

        String jsonStr = "{\"list\":[{\"type\":1,\"data\":{\"title\":\"title0\"}},{\"type\":1,\"data\":{\"title\":\"title1\"}},{\"type\":2,\"data\":{\"luck\":\"luck0\"}},{\"type\":2,\"data\":{\"luck\":\"luck1\"}}]}";
        Contains contains2 = GsonUtils.fromJson(jsonStr, Contains.class);
        for (ContainsItem containsItem : contains2.getList()) {
            if (containsItem.type == 1) {
                DataA dataA = containsItem.getBeans();
                DataA dataA2 = containsItem.getBeans();
                System.out.println("dataA==dataA2:" + (dataA == dataA2) + "\t 内容:" + GsonUtils.toJson(dataA));
            }
            if (containsItem.type == 2) {
                DataB dataB = containsItem.getBeans();
                DataB dataB2 = containsItem.getBeans();
                System.out.println("dataB==dataB2:" + (dataB == dataB2) + "\t 内容:" + GsonUtils.toJson(dataB));
            }
        }

    }


    public static class Contains {
        List<ContainsItem> list = new ArrayList<>();

        public List<ContainsItem> getList() {
            return list;
        }

        public void setList(List<ContainsItem> list) {
            this.list = list;
        }
    }

    //    public static class ContainsItem {
//        int type;
//        Object data;//list用
//
//        public ContainsItem(int type, Object data) {
//            this.type = type;
//            this.data = data;
//        }
//    }
    public static class ContainsItem {

        public ContainsItem(int type, JsonElement data) {
            this.type = type;
            this.data = data;
        }

        int type;
        JsonElement data;//list用
        Object obj;//list用

        public <T> T getBeans() {
            if (obj != null)
                return (T) obj;
            else {
                if (type == 1) {
                    obj = (T) new Gson().fromJson(data, DataA.class);
                } else {
                    obj = (T) new Gson().fromJson(data, DataB.class);
                }
                return (T) obj;
            }
        }
    }

    public static class DataA {
        private String title;

        public DataA(String title) {
            this.title = title;
        }

    }

    public static class DataB {
        private String luck;

        public DataB(String luck) {
            this.luck = luck;
        }
    }

}
