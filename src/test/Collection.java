package test;

import java.text.DateFormat;
import java.util.*;

/**
 * @Author:lkn
 * @Description:
 * @Date:Created in 2019/2/12 14:21
 * @Modified By:
 */
public class Collection {
    //TODO
    public void maptest(){
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i,i);
        }
        Set<Integer> integers = map.keySet();
        System.out.println(integers);

        java.util.Collection<Object> values = map.values();
        System.out.println(values);

        Iterator<Map.Entry<Integer, Object>> iter = map.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<Integer, Object> next = iter.next();
            System.out.println("k"+next.getKey());
            System.out.println("v"+next.getValue());
        }
    }
    public static void main(String[] args) {

    }
}
