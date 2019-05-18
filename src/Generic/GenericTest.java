package Generic;

import entity.Generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:kn
 * @ Description:    1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
 *                   2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
 *                   3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
 *                   4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
 * @ Date:Created in 2019/2/27 10:55
 * @ Modified By:
 */
public class GenericTest {
    public void getkey(Generic<? extends Number> num){
        System.out.println("val:"+num.getKey());
    }

    /**
     * /在泛型方法中添加上下边界限制的时候，必须在权限声明与返回值之间的<T>上添加上下边界，即在泛型声明的时候添加
     * @param generic
     * @param <T>
     * @return
     */
    public <T extends Number> T showKey(Generic<T> generic){
        T key = generic.getKey();
        return key;
    }

    public <T> void print(T... args){
        for (int i = 0; i < args.length; i++) {
            T arg = args[i];
            System.out.println(arg);
        }
    }

    /**
     * 静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。
     * @param t
     * @param <T>
     */
    public static <T> void show(T t){

    }

    public <T> List<T> addList(T... t){
        ArrayList<T> list = new ArrayList<>();
        for (T t1 : t) {
            list.add(t1);
        }
        return list;
    }

    public static void main(String[] args) throws Exception{
        GenericTest test = new GenericTest();
        Generic<Integer> a = new Generic<>(12);
        test.getkey(a);

        List<? extends Serializable> list = test.addList(12, "asdfs", 12.01, false);
        System.out.println(list.getClass().getTypeName());
    }
}
