package juc.assistTool;

import java.util.concurrent.Exchanger;

/**
 * @ Author:kn
 * @ Description: 交换空间：Exchanger
 * 如果说现在有两个线程，一个线程负责生产数据，另外一个线程负责消费数据，那么这两个线程之间一定会存在有一个公共的区域，那么这个公共区域的实现在juc包之中就成为Exchanger。
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        //两个消费者
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                while (true){
                    try {
                        String exchange = exchanger.exchange(null);
                        if (exchange!=null){
                            System.out.println(Thread.currentThread().getName()+"取的数据:"+exchange);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
        //4个生产者
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            new Thread(()->{
                String data="data"+"-"+ finalI;
                try {
                    //插入交换空间
                    exchanger.exchange(data);
                    System.out.println(Thread.currentThread().getName()+"生产数据:"+data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
