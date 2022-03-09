package gson学习与反射;

import com.google.gson.Gson;

public class GsonTest {

    static String json1 = "{\n" +
            "    \"section\": {\n" +
            "        \"music\": {\n" +
            "            \"sign\": \"music\",\n" +
            "            \"name\": \"首页\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"section2\": {\n" +
            "        \"music\": {\n" +
            "            \"sign\": \"music2\",\n" +
            "            \"name\": \"首页\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    static String json2 = "{\n" +
            "    \"section\": {\n" +
            "        \"music\": {\n" +
            "            \"sign\": \"music\",\n" +
            "            \"name\": \"首页\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"section2\": {\n" +
            "        \"music\": {\n" +
            "            \"sign\": \"music2\" ,\n" +
            "            \"sign2\": \"music2\"          \n" +
            "        }\n" +
            "    }\n" +
            "}";


    public static void main(String[] args) {

        Gson gson = new Gson();
        JsonA jsonA = gson.fromJson(json1, JsonA.class);
        JsonA jsonA2 = gson.fromJson(json2, JsonA.class);

        System.out.println();
    }
}
