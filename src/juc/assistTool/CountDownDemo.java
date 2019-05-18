package juc.assistTool;

import java.util.concurrent.CountDownLatch;

/**
 * @ Author:kn
 * @ Description: 闭锁：CountDownLatch  这个类能够使一个线程等待其他线程执行完各自的工作再执行
 * 通过一个计数器实现,初始值线程的数量,每当一个线程执行完自己的任务,计数器减一,计数器为零说明所有线程完成任务,在闭锁上等待的线程可以恢复执行任务
 *  构造方法：public CountDownLatch(int count),要设置一个等待的线程个数；
 *        减少等待个数：public void countdown();
 *        等待countDownLatch为0：public void await() throws InterruptedException;
 */
public class CountDownDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"执行");
                countDownLatch.countDown();//计数器减一
            }).start();

        }
        countDownLatch.await();//等待计数结束,计数器=0
        System.out.println("主程序执行");
    }

}
