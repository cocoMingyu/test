package test;

import entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:lkn
 * @Description:
 * @Date:Created in 2019/1/15 10:50
 * @Modified By:
 */
public class EqualsAndHashcode {
    public static void main(String[] args) {
        List<User> list=new ArrayList();
        Set<User> set=new HashSet<>();
        User u1=new User("a",13);
        System.out.println("u1:"+u1.hashCode());
        User u2=new User("a",13);
        System.out.println("u2:"+u2.hashCode());

        list.add(u1);
        list.add(u2);

        set.add(u1);
        set.add(u2);

        System.out.println("u1.equals(u2):"+u1.equals(u2));
        System.out.println("u1=========u2:"+ (u1==u2));
        System.out.println("list size:"+list.size());
        System.out.println("set size:"+set.size());

    }
}
