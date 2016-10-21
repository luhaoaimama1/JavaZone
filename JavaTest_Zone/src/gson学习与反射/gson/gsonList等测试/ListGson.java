package gson学习与反射.gson.gsonList等测试;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuzhipeng on 2016/10/18.
 */
public class ListGson {
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
        Person[] ps3 = gson.fromJson(str,Person[].class);
        List<Person> ps4 = GsonUtils.fromJsonToList(str, Person.class);
        Person pTemp = new Person();
        pTemp.setName("name" + 100);
        pTemp.setAge(100 * 5);
        ps4.add(pTemp);

        for (int i = 0; i < ps4.size(); i++) {
            Person p = ps4.get(i);
            System.out.println("渣渣:" + p.toString());
        }
    }

}
