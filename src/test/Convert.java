package test;

import entity.User;
import utils.ConvertUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/3/4 17:09
 * @ Modified By:
 */
public class Convert {
    public void test1(){
        User user = new User("aaa", 30, 100, new BigDecimal(185));
        Map<String, Object> map = ConvertUtils.beanToMap(user);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"  "+entry.getValue()+" "+entry.getValue().getClass().getTypeName());
        }
    }

    public void test2(){
        List<User> users = new ArrayList<>();
        users.add(new User("X",1));
        users.add(new User("XXL",2));
        users.add(new User("S",3));
        /*users.add(new User("L",4));
        users.add(new User("XXXL",5));*/
        Integer integer = users.stream().map(User::getAge).reduce(0,(a, b) -> a += b);
        Integer reduce = users.stream().parallel().map(User::getAge).reduce(1, (a, b) -> a + b);
        Integer reduce1 = users.stream().parallel().map(User::getAge).reduce(2, (a, b) -> a + b, (a,b) ->a-b);
        Integer reduce3 = users.stream().map(a ->a.getAge()+2).reduce((a,b) ->a-b).get();
        Integer reduce2 = users.stream().map(User::getAge).reduce(0, (a, b) -> a + b);
        System.out.println(integer);
    }

    public static void main(String[] args) {
        Convert convert = new Convert();
        //convert.test1();
        convert.test2();
    }
}
