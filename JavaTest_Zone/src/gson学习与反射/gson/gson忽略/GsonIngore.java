package gson学习与反射.gson.gson忽略;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Created by fuzhipeng on 2016/10/14.
 */
public class GsonIngore {

    private String id;
    private  transient String name;
    @Expose
    private   String expose;

    public String getExpose() {
        return expose;
    }

    public void setExpose(String expose) {
        this.expose = expose;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    public static void main(String[] args) {
//        GsonIngore gs = new GsonIngore();
//        gs.setId("id?");
//        gs.setName("zone");
//        gs.setExpose("expose");
//        System.out.println(  new Gson().toJson(gs));
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
//                .create();
//        System.out.println(gson.toJson(gs));
//
//    }

    public static void main(String[] args) {
           String abc="hello  world";
    }
}
