package gson学习与反射.gson.gsonList等测试;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2016/10/18.
 */
public class ListGson泛型擦除<K> {
    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setName("name" + i);
            p.setAge(i * 5);
            persons.add(p);
        }
        String str = gson.toJson(persons);

        List<Person> ps2 = gson.fromJson(str, new TypeToken<List<Person>>() {
        }.getType());
        Person[] ps3 = gson.fromJson(str, Person[].class);
        List<Person> ps4 = GsonUtils.fromJsonToList(str, Person.class);
        Person pTemp = new Person();
        pTemp.setName("name" + 100);
        pTemp.setAge(100 * 5);
        ps4.add(pTemp);

        for (int i = 0; i < ps4.size(); i++) {
            Person p = ps4.get(i);
            System.out.println("渣渣:" + p.toString());
        }

        //泛型擦除的亮点  ！！！！！




        //静态 可以匹配任何
         ListGson泛型擦除.get(ListGson泛型擦除.<List>getBean2(str));
         ListGson泛型擦除.get(ListGson泛型擦除.getBean2(str));
        //对象内的需要精确匹配 就是类对类  泛型对泛型
        ListGson泛型擦除.get(new ListGson泛型擦除().getBean5(str));
        ListGson泛型擦除.get( new ListGson泛型擦除().getBean4(str));

        //// FIXME: 2017/4/9  error!
//        ListGson泛型擦除.get( new ListGson泛型擦除().getBean3(str));
    }



    //todo //对象内的需要精确匹配  既类对 类型对类型 泛型对泛型
    public  <B> List<B> getBean5(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, new TypeToken<List<Person>>() {
        }.getType());
    }
    /**
     * Unchecked assignment: 'java.util.List' to 'java.util.List<gson学习与反射.gson.gsonList等测试.Person>'.
     * Reason: 'new ListGson泛型擦除()' has raw type, so result of getBean4 is erased less...
     * @param str
     * @return
     */
    public List<K> getBean4(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, new TypeToken<List<Person>>() {
        }.getType());
    }

    //todo Error
    public K getBean3(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, new TypeToken<List<Person>>() {
        }.getType());
    }

    //静态的泛型 可以匹配任何 但是运行时如果强转报错 就尴尬了
    public static <E> E getBean2(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, new TypeToken<List<Person>>() {
        }.getType());
    }


    //这里自动转型了！
    public static void get(List<Person> per) {
        System.out.println(per.get(0).getAge());
    }



}
