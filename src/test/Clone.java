package test;

import entity.User;

import java.util.*;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/3/4 11:42
 * @ Modified By:
 */
public class Clone {
    public static void main(String[] args) throws CloneNotSupportedException {
        List list=new ArrayList();
/*        User user = new User("abc", 13,2000);
        User clone = user.clone();
        list.add(clone);
        System.out.println(Math.pow(2,5));*/

        List<String> list1=new ArrayList<>();
        list1.add("a");
        list1.add("b");
        List<String> list2=new ArrayList<>();
        list2.add("b");
        list2.add("c");
        list.addAll(list1);

        for (String s : list2) {
            if (!list.contains(s)){
                list.add(s);
            }
        }


        System.out.println(list.toString());
    }

}
