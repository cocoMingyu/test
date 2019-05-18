package test;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Author:lkn
 * @Description:
 * @Date:Created in 2018/11/15 10:02
 * @Modified By:
 */
public class optional {
    static final Map<String,Object> map=new Hashtable<>();

    public static void main(String[] args) {
        optional1();
    }
    public static void optional1(){
        map.put("name","jim");
        map.put("name2","tom");
        map.put("age",12);
        Optional<Map<String, Object>> map = Optional.ofNullable(optional.map);
        Optional<Object> nullvalue = Optional.ofNullable(null);

        if(map.isPresent()){
            System.out.println(map.get());
            System.out.println(map.get().get("name"));
        }

        map.ifPresent(System.out::println);

        try {
            Object o = nullvalue.get();
            System.out.println(o);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        map.ifPresent(a ->{
            a.put("sex","man");
        });
        Optional<Object> name = map.map(a -> a.get("name"));
        System.out.println(name.orElse("kenan"));

        Optional<Object> name1 = map.flatMap(a -> Optional.ofNullable(a.get("name1")));
        System.out.println(name1.orElse("mingyu"));

        Optional<Map<String, Object>> filtermap = map.filter(a -> a.get("age").equals(13));
        System.out.println(filtermap.orElse(null));

    }
}
