package juc;

import java.util.concurrent.locks.LockSupport;

/**
 * @ Author:kn
 * @ Description:java.util.concurrent.locks.LockSupport这是一个独立的类，这个类的主要功能是用来解决Thread类里面提供的suspend()（挂起线程）、resume()(回复运行)方法，
 * 这两个方法本质上会存在有死锁的嫌疑，所以从JDK1.4开始将其就已经列为不建议使用的方法了，但在JDK开发JUC的架构之后，考虑到JUC架构之中的各种实现机制，于是开始试图还原之前被废弃的操作，
 * 于是有了LockSupport类，这个类里面有两个方法：
 *        挂起：public static void park(Object blocker);
 *        恢复：public static void unpark(Thread thread);
 */
public class LockSupportDemo {
    public static String msg = null ;  // 设置一个字符串
    public static void main(String[] args) throws Exception {
        // 获得当前的线程操作类
        Thread mainThread = Thread.currentThread();
        new Thread(() -> {
            try {
                msg = "www.baidu.com";
            } finally {  // 解锁关起状态
                LockSupport.unpark(mainThread);
            }
        }).start();
        LockSupport.park(mainThread);
        System.out.println("********** 主线程执行完毕,msg=" + msg);
    }
}
