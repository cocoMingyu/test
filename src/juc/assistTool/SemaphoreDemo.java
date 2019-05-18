package juc.assistTool;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ Author:kn
 * @ Description:
 * 【 信号量：Semaphore 】[ˈseməfɔː(r)]
 * ➣ Semaphore通常用于限制可以访问某些资源（物理或逻辑的）的线程数目。
 * ➣ 例如，大家排队去银行办理业务，但是只有两个银行窗口提供服务，来了10个人需要排队，所以这10个排队的人员就需要依次使 用这两个业务窗口。
 *
 *   首先来观察java.util.concurrent.Semaphore类的基本定义形式： 
 *   public class Semaphore extends Object implements Serializable
 *
 *   Semaphore类中定义的方法有如下几个：
 *      构造方法：public Semaphore(int permits)设置服务的信号数量。
 *      构造方法：public Semaphore(int permits, Boolean fair)是否为公平锁。
 *      等待执行：public void acquireUninteruptibly(int permits)
 *      |- 设置的信号量上如果有阻塞的线程对象存在，那么讲一直持续阻塞状态；
 *      释放线程的阻塞状态：public void release(int permits);
 *      返回可用的资源个数：public int availablePermits();
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws Exception {
        // 现在允许操作的资源一共有2两个
        Semaphore sem = new Semaphore(2);
        // 模拟每一个用户办理业务的时间
        Random rand = new Random();
        for (int x = 0; x < 10; x++) {
            // 每一个线程就是一个要办理业务的人员
            new Thread(() -> {
                // 现在有空余窗口
                if (sem.availablePermits() > 0) {
                    System.out.println(Thread.currentThread().getName() + "进入银行，此时银行没有人排队");
                } else {   // 没有空余位置
                    System.out.println("【" + Thread.currentThread().getName() + "】排队等待办理业务。");
                }
                try {
                    // 从信号量中获得操作许可
                    sem.acquire();
                    System.out.println(Thread.currentThread().getName() + "｛start｝开始办理业务。");
                    // 模拟办公延迟
                    TimeUnit.SECONDS.sleep(rand.nextInt(10));
                    System.out.println("【" + Thread.currentThread().getName() + "】｛end｝结束办理业务。");
                    // 当前线程离开办公窗口
                    sem.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "顾客-" + x).start();
        }
    }
}
