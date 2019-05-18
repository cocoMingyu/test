package juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ Author:kn
 * @ Description:【 读写锁：ReadWriteLock 】
 * 所谓的读写锁指的是有两把锁，在进行数据写入的时候有一把”写锁”，而在进行数据读取的时候有一把”读锁”，很明显写锁一定会实现线程安全同步处理操作，而读锁可以被多个对象读取获得。
 * ➣ 分为读锁和写锁，多个读锁不互斥（共享锁），读锁与写锁互斥，这是由jvm自己控制的，开发者只要上好相应的锁即可；
 * ➣ ReentrantReadWriteLock会使用两把锁来解决问题，一个读锁（多个线程可以同时读），一个写锁（单个线程写）。
 * ➣ ReadLock可以被多个线程持有并且在作用时排斥任何的WriteLock，而WriteLock则是完全的互斥，这一特性最为重要，因为对于高读取频率而相对较低写入的数据结构，使用此类锁同步机制则可以提高并发量；
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        double[] moneyarr={10,20,30,50,100,200};
        Account kn = new Account("kn", 20);
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(()->{
                kn.write(moneyarr[finalI]);
            }).start();
        }
        for (int i = 0; i < moneyarr.length; i++) {
            new Thread(()->{
                    kn.read();

            }).start();
        }

    }
}
class Account{
    private String name;
    private double money;

    public Account(String name, double money) {
        this.name = name;
        this.money = money;
    }
    //读写锁
    private ReadWriteLock lock= new ReentrantReadWriteLock();

    public void write(double addmoney){
        lock.writeLock().lock();
        try {
            if (addmoney>0){
                System.out.println(Thread.currentThread().getName()+"进行存钱操作,当前余额:"+money);
                money=money+addmoney;
            }
        } finally {
            System.out.println(Thread.currentThread().getName()+"进行存钱操作,存钱后余额:"+money);
            lock.writeLock().unlock();
        }
    }
    public void read(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取操作,余额:"+money);
        }finally {
            lock.readLock().unlock();
        }
    }
}
