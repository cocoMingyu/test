package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Author:kn
 * @ Description:【 锁的精确控制：Condition 】
 *    在之前已接触过了一些基础的锁，但在进行处理的时候还有一个接口Condition，这个接口可以由用户来自己进行锁的对象创建。
 * ➣ Condition的作用是对锁进行更精确的控制。Condition中的await()方法相当于Object的wait()方法，Condition中的signal()方法相当于Object的notify()方法，
 * Condition中的signalAll()相当于Object的notifyAll()方法。不同的是，Object中的wait()、notify()、notifyAll()方法是和”同步锁”/“共享锁”捆绑使用的。
 */
public class ConditionDemo {
    public static void main(String[] args) {
        ConditionData<String> data=new ConditionData<String>();
        for (int i = 0; i < 5; i++) {
            int j = i;
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.put(Thread.currentThread().getName()+"存入数据 x="+ j);
            },"生产者"+i).start();
        }
        for (int i = 0; i < 5; i++) {
            int j = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"读取数据 value="+data.get());
                data.get();
            },"消费者"+i).start();
        }
    }
}
//进行数据的缓冲的操作机制,可以保存各种类型数据
class ConditionData<T>{
    //数组长度
    private final static int MAX_LENGTH=5;
    //定义数组进行全局数据保存
    private Object[] data=new Object[MAX_LENGTH];
    private Lock lock=new ReentrantLock();
    //数据保存的condition控制
    private Condition putCondition=lock.newCondition();
    //数据读取的condition控制
    private Condition getCondition=lock.newCondition();
    //保存索引
    private int putindex=0;
    //读取索引
    private int getindex=0;
    //当前保存元素个数
    private int count=0;

    public void put(T t){
        lock.lock();
        try {
            if (t==null||this.count==MAX_LENGTH){
                this.putCondition.await();
            }
            this.data[this.putindex++]=t;
            if (putindex==MAX_LENGTH){//索引满了,重置
                putindex=0;
            }
            System.out.println(Thread.currentThread().getName()+"写入缓冲,value="+t);
            count++;
            //唤醒其他线程
            getCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get(){
        this.lock.lock();
        Object t=null;
        try {
            if (count==0){//没有写入,读取进程等待
                this.getCondition.await();
            }
            t=this.data[getindex++];
            if (getindex==MAX_LENGTH){
                getindex=0;
            }
            count--;//读取一个数据,减一
            putCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
        return (T)t;
    }
}
