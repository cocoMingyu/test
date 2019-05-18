package juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ Author:kn
 * @ Description:
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(3);
        ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue<String>(5);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                    String str=Thread.currentThread().getName()+"   生产数据:"+ finalI;
                    System.out.println(str);
                    arrayBlockingQueue.put(str);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"生产者"+i).start();
        }

        countDownLatch.await();
        String peek = arrayBlockingQueue.peek();
        String poll = arrayBlockingQueue.poll();
        /*for (int i = 0; i < 4; i++) {
            int num=i;
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    if (arrayBlockingQueue.isEmpty()) {
                        break;
                    }
                        System.out.println(Thread.currentThread().getName() + "   消费数据:" + arrayBlockingQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "消费者" + i).start();
        }*/
    }
}
