package juc.assistTool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ Author:kn
 * @ Description:线程回调：CompletableFuture
 * 所有的执行线程在接收到命令之前都要进入到阻塞状态之中，一直到接收到具体命令之后才会执行下一步的操作处理。
 * ➣ CompletableFuture是java8中添加的一个类，该类主要的作用就是提供了新的方式来完成异步处理，包括合成和组合事件的非阻塞方式。
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"准备开始干活");
                try {
                    String s = future.get();
                    if ("true".equals(s)){
                        System.out.println(Thread.currentThread().getName()+"兄弟们抄家伙干");
                    }else {
                        System.out.println(Thread.currentThread().getName()+"风紧扯呼");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        future.complete("false");//给出命令

    }

}


