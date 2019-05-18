package juc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @ Author:kn
 * @ Description:
 【 并发集合工具类 】
 ➣ 为了更好的实现集合的高兵法访问处理，创建了一组心的集合工具类。
    ➣ List和Set集合：
         ➣ CopyOnWriteArrayList相当于线程安全的ArrayList，实现了List接口。
             CopyOnWriteArrayList是支持高并发的；
         ➣ CopyOnWriteArraySet相当于线程安全的HashSet，它继承了AbstractSet类，
             CopyOnWriteArraySet内部包含一个CopyOnWriteArrayList对象，
             它是通过CopyOnWriteArrayList实现的。
    ➣ Map集合：
         ➣ ConcurrentHashMap是线程安全的哈希表（相当于线程安全的HashMap）；
             它继承于AbstractMap类，并且实现ConcurrentMap接口。
             ConcurrentHashMap是通过“锁分段”来实现的，它支持并发；
         ➣ ConcurrentSkipListMap是线程安全的有序的哈希表（相当于线程安全的TreeMap）；
             它继承于AbstactMap类，并且实现ConcurrentNavigableMap接口。
             ConcurrentSkipListMap是通过“跳表”来实现的，它支持并发；
         ➣ ConcurrentSkipListSet是线程安全的有序的集合（相当于线程安全的TreeSet）;
             它继承于AbstractSet，并实现了NavigableSet接口。
             ConcurrentSkipListSet是通过ConcurrentSkipListMap实现的，它也支持并发；
   ➣ Queue队列：
        ➣ ArrayBlockingQueue是数组实现的线程安全的有界的阻塞队列；
        ➣ LinkedBlockingQueue是单向链表实现的（指定大小）阻塞队列，该队列按FIFO（先进先出）排序元素；
        ➣ LinkedBlockingDeque是双向链表实现的（指定大小）双向并发阻塞队列，
           该阻塞队列同时支持FIFO和FILO两种操作方式；
        ➣ ConcurrentLinkedQueue是单向链表实现的无界队列，该队列按FIFO（先进先出）排序元素。
        ➣ ConcurrentLinkedDeque是双向链表实现的无界队列，该队列同时支持FIFO和FILO两种操作方式。
 */
public class CollectionDemo {
    public static void main(String[] args) {
        testConcurrentMap();
    }

    public static void testConcurrentMap(){
        ConcurrentHashMap map=new ConcurrentHashMap();
        int i = 1 << 31;
        System.out.println(i);
    }

    public static void testMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("a","aaa");
        map.put("b","bbb");
        map.put("c","ccc");
        map.put("d","ddd");
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        Set<String> strings = map.keySet();
        map.computeIfAbsent("e",setFunction("we"));
    }

    public static Function setFunction(String s){
        Function function=new Function() {
            @Override
            public Object apply(Object o) {
                return s+"111";
            }
        };
        return function;
    }
}
