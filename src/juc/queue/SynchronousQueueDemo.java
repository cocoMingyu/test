package juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ Author:kn
 * @ Description:【 同步队列：SynchronousQueue 】
 * 之前使用BlockingQueue每一次都可以保存多个数据对象信息，但是有些时候只能够允许你保存一个数据的信息，这种情况下就要使用SynchronousQueue子类完成。
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws Exception {
        // 允许保存5个数据队列
        BlockingQueue<String> queue = new SynchronousQueue<String>();
        for (int x = 0; x < 3; x++) {
            new Thread(() -> {
                for (int y = 0; y < 5; y++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        String str = "【生产数据｛" + Thread.currentThread().getName() + "｝】y = " + y;
                        queue.put(str);    // 会进入到生产的阻塞状态
                        System.out.println(str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "生产者-" + x).start();
        }
        for (int x = 0; x < 5; x++) {
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("【消费数据｛" + Thread.currentThread().getName() + "｝】" + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "消费者-" + x).start();
        }
    }

}
