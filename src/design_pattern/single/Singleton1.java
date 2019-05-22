package design_pattern.single;

/**
 * @ Author:kn
 * @ Description:懒汉式(延迟加载)，在多线程的情况下会产生多个single对象，使用synchronized同步锁
 * @ Date:Created in 2019/5/21 17:02
 * @ Modified By:
 */
public class Singleton1 {
    private static Singleton1 singleton1;

    private Singleton1() {
    }

    public static Singleton1 getInstance(){
        if (singleton1==null){
            synchronized (Singleton2.class){
                singleton1=new Singleton1();
            }
        }
        return singleton1;
    }
}
