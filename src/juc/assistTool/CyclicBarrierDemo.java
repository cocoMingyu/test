package juc.assistTool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ Author:kn
 * @ Description:栅栏：CyclicBarrier  [saɪklɪk]
 * CyclicBarrier和CountDownLatch是非常相似的，CyclicBarrier核心的概念是在于设置一个等待线程的数量边界，到达了此边界之后进行执行。
 * 一个同步辅助类,允许一组线程互相等待,直到到达某个公共屏障点
 * CyclicBarrier 是一种同步机制,能够对一些算法的线程实现同步.换句话说,是所有线程必须等待的栅栏,直到所有的线程都到达这里,然后所有的线程才可以继续做其他事情
 * await方法,两个线程实现互相等待,一旦n个线程在等待CyclicBarrier达成,所有线程被释放,继续执行
 * 构造方法：public CyclicBarrier(int parties), 设置等待的边界；
 *      傻傻等待其它线程：public int await() throws InterruptedException, BrokenBarrierException;
 *      等待其它线程：public int await(long timeout, TimeUnit unit) throws InterruptedException, BrokenBarrierException, TimeoutException。
 *      重置等待线程个数：public void reset()。
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //凑够线程个数-parties,才能进行触发,否则一直等待
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 3; i++) {
            int time=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"正在等待");
                try {
                    if (time==2){
                        cyclicBarrier.reset();//重置
                        System.out.println(Thread.currentThread().getName()+"进行重置");
                    }else {
                        TimeUnit.SECONDS.sleep(time);
                        cyclicBarrier.await(50,TimeUnit.SECONDS);//等待处理,如果不想一直等待则可以设置超时时间，则超过了等待时间之后将出现”TimeoutException”
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"等待结束");
            }).start();

        }
    }
}
