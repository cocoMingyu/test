package test;

import entity.User;
import utils.ConvertUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/2/26 9:53
 * @ Modified By:
 */
public class lamda {
    /**
     * 消费型接口
     * 有参数，无返回值类型的接口
     */
    public void Customer(Integer i, Consumer<Integer> con){
        con.accept(i);
    }
    /**
     * 供给型接口
     * 只有产出，没人输入，就是只有返回值，没有入参
     */
    public List<Integer> Supplier(int size, Supplier<Integer> sup){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integer integer = sup.get();
            list.add(integer);
        }
        return list;
    }

    /**
     * 函数型接口 输入一个类型得参数，输出一个类型得参数，当然两种类型可以一致
     * @param str
     * @param fun
     * @return
     */
    public Integer function(String str, Function<String,Integer> fun){
        return fun.apply(str);
    }

    /**
     * 断言型 输入一个参数，输出一个boolean类型得返回值。
     * @param num
     * @param pre
     * @return
     */
    public Integer Predicate(Integer num, Predicate<Integer> pre){
        int number=0;
        if (pre.test(num)){
            number= num*2;
        }
        return number;
    }

    public void parallel(){
        Instant now = Instant.now();
        LongStream.rangeClosed(0,1000000000000L)
                .parallel()
                .reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时"+ Duration.between(now,end).toMillis());
    }

    public void streamTest(){
        List<User> users = new ArrayList<>();
        users.add(new User("X",12));
        users.add(new User("XXL",12));
        users.add(new User("S",199));
        users.add(new User("L",199));
        users.add(new User("XXXL",32));
        List<User> collect1 = users.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
        System.out.println(collect1);
        /**
         * nullsLast null值放在最后
         * nullsFirst NULL值放在最前面
         */
        List<User> collect2 = users.stream().sorted(Comparator.comparing(User::getName, Comparator.nullsLast(String::compareTo))).collect(Collectors.toList());
        /*Map<String, List<User>> listMap = users.stream().collect(Collectors.groupingBy(User::getName));
        List<User> collect1 = listMap.values().stream().map(a -> a.stream().min(Comparator.comparing(User::getAge)).get()).collect(Collectors.toList());*/
        System.out.println(collect2);
        ArrayList<User> collect = users.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(a ->a.getName()+a.getAge()))), ArrayList::new));
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void main(String[] args) {
        lamda lamda = new lamda();
        lamda.Customer(100,t -> System.out.println(t));
        List<Integer> supplier = lamda.Supplier(10, () -> (int)(Math.random() * 100));
        supplier.stream().forEach(a -> System.out.print(a+"\t"));
        Integer str = lamda.function("sadfgdf", a -> a.length());
        System.out.println("string:"+str);

        Integer predicate = lamda.Predicate(10, a -> a % 2 == 0);
        Function<Integer,String[]> fun=String[]::new;
        String[] apply = fun.apply(10);
        lamda.parallel();
        lamda.streamTest();
    }
}
