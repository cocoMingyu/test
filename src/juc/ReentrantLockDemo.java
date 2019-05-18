package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Author:kn
 * @ Description: ReentrantLock是一个可重入的互斥锁，又被称为”独占锁”。
 * ➣ ReentrantLock锁在同一个时间点只能被一个线程锁持有；而可重入的意思是，
 *   ReentrantLock锁，可以被单个线程多次获取。
 * ➣ ReentrantLock分为”公平锁”和“非公平锁”。它们的区别体现在获取锁的机制
 *   上是否公平以及执行速度上。
 * ➣ ReentrantLock是通过一个FIFO的等待队列来管理获取该锁所有线程的。
 *    ReentrantLock是一个独占锁，在获取锁的之后其所有的操作是线程独享的，其它的线程在没有获取到锁之前都需要进行等待。
 * @ Date:Created in 2019/4/24 19:34
 * @ Modified By:
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();//多线程共享一个资源
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                ticket.sale();
            }).start();
        }
    }
}
class Ticket{
    private int ticketcount=10;
    private Lock reentrantLock= new ReentrantLock();
    /*描述与锁有关的条件变量
    private Condition condition=reentrantLock.newCondition();
    condition.await(); 等待
    condition.signal(); 唤醒
    condition.signalAll(); 唤醒全部*/
    public void sale(){
        reentrantLock.lock();//进入阻塞状态,一直到unlock解除状态
        try {
            if (this.ticketcount>0){
                System.out.println(Thread.currentThread().getName()+"卖票,ticket="+this.ticketcount--);
            }
        } finally {//不管最终结果如何一定要解锁
            reentrantLock.unlock();
        }
    }
}
