package json;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import pojo.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {

    // javaBean和json的互转
    @Test
    public void test1() {
        Person person = new Person(1001, "小明");
        // 创建Gson对象实例
        Gson gson = new Gson();

        // toJson()：把java对象转换成为json字符串
        String person1 = gson.toJson(person);
        System.out.println("java对象—>json：" + person1);

        // fromJson()：把json字符串转换回Java对象
        // 第一个参数是json字符串
        // 第二个参数是转换回去的Java对象类型
        Person person2 = gson.fromJson(person1, Person.class);
        System.out.println("json—>java对象" + person2);

    }

    // List集合 和json的互转
    @Test
    public void test2() {
        //准备list集合
        List<Person> personList = new ArrayList<>();
        //向集合添加数据
        personList.add(new Person(1, "小明"));
        personList.add(new Person(2, "小刚"));

        Gson gson = new Gson();

        // 把List转换为json字符串
        String List1 = gson.toJson(personList);
        System.out.println("List集合—>json字符串：" + List1);

        List<Person> list = gson.fromJson(List1, new PersonListType().getType());
        System.out.println("json字符串—>List集合：" + list);

    }

    // map集合和json的互转
    @Test
    public void test3() {
        Map<Integer, Person> personMap = new HashMap<>();

        personMap.put(1, new Person(1, "小明"));
        personMap.put(2, new Person(2, "小刚"));

        // 创建Gson对象实例
        Gson gson = new Gson();
        // 把 map 集合转换成为 json字符串
        String Map1 = gson.toJson(personMap);
        System.out.println("map集合—>json字符串：" + Map1);

        // Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new PersonMapType().getType());
        Map<Integer, Person> personMap2 = gson.fromJson(Map1, new TypeToken<HashMap<Integer, Person>>() {}.getType());
        System.out.println("json字符串—>map集合：" + personMap2);
    }


}
