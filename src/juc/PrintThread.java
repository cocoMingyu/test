package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/4/24 20:41
 * @ Modified By:
 */
public class PrintThread {

    public static void main(String[] args) {
        print printThread=new print();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printThread.thread1();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printThread.thread2();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printThread.thread3();
            }
        }, "C").start();

    }
}
class print{
    private int number=1;//正在执行线程的索引
    Lock lock=new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    public void thread1(){
        lock.lock();
        try {
            if (number != 1) {
                condition1.await();
            }
            System.out.print(Thread.currentThread().getName());
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void thread2(){
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            System.out.print(Thread.currentThread().getName());

            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void thread3() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            System.out.print(Thread.currentThread().getName());

            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
