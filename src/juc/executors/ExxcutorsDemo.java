package juc.executors;

import org.springframework.ws.soap.addressing.core.MessageAddressingProperties;

import java.util.*;
import java.util.concurrent.*;

/**
 * @ Author:kn
 * @ Description:【 线程池分类 】
 * ➢ java.util.concurrent.Executors类可以创建线程池
 * ➣ 创建无大小限制的线程池 : public static ExecutorService newCacheThreadPool();
 * ➢ 创建固定大小的线程池 : public static ExecutorService newFixedThreadPool(int nThreads);
 * ➣ 单线程池 : public static ScheduledExecutorService newSingleThreadScheduledExecutor();
 * ➣ 创建定时调度池 : public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize).
 *
 * 【 创建四种线程池 】
 * 下面将具体演示四中线程池的创建以及其自身的使用特点。当Executors创建完成了线程池之后可以返回“ExecutorService”接口对象，而这个接口对象里面有两个方法来接收线程的执行：
 * ★ 接收Callable: public <T> Future<T> submit(Callable<T> task);
 * ★ 接收Runnable: public Future<?> submit(Runnable task);
 */
public class ExxcutorsDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        invokeAllDemo();
    }
    //创建无限量线程池
    public static void test1(){
        //创建一个线程池
        ExecutorService service= Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.submit(()->{//线程池会负责启动
                System.out.println(Thread.currentThread().getName()+"start");
            });
        }
        service.shutdown();//线程池启动后关闭

        Callable callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };
    }

    //创建有限线程池
    public static void test2(){
        //创建一个容量为3的线程池
        ExecutorService service= Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            service.submit(()->{//线程池会负责启动
                System.out.println(Thread.currentThread().getName()+"start");
            });
        }
        service.shutdown();//线程池执行完后关闭
    }

    //创建单例线程池
    public static  void test3(){
        ExecutorService service= Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            service.submit(()->{//线程池会负责启动
                System.out.println(Thread.currentThread().getName()+"start");
            });
        }
        service.shutdown();//线程池执行完后关闭
    }

    //创建一个定时调度池，这个调度池主要以间隔调度为主。如果要创建调度池则肯定使用ScheduledExecutorService接口完成，在该接口之中包含有如下的两个方法：
    //延迟启动：public ScheduledFuture<?>schedule(Runnable command, long delay, TimeUnit unit);
    //间隔调度：ScheduledFuture<?>scheduleAtFixedRate(Runnable command,long initialDelay, long period, TimeUnit unit);
    public static  void test4(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 2; i++) {
            service.schedule(()->{//线程池会负责启动
                System.out.println(Thread.currentThread().getName()+"start");
            },2, TimeUnit.SECONDS);
        }
        service.shutdown();//线程池执行完后关闭
    }

    public static  void test5(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 2; i++) {
            service.scheduleAtFixedRate(()->{//线程池会负责启动
                System.out.println(Thread.currentThread().getName()+"start");
            },2,2, TimeUnit.SECONDS);
        }
        //service.shutdown();//线程池执行完后关闭
    }

    public static void test6() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i <4; i++) {
            Future<?> future = service.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "执行");
            });
            System.out.println(future.get());
        }
        service.shutdown();
    }

    public static void invokeAnyDemo(){
        try {
            HashSet<Callable<String>> set = new HashSet<>();
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                set.add(() -> {
                    return Thread.currentThread().getName() + finalI;
                });
            }
            ExecutorService service = Executors.newCachedThreadPool();
            String s = service.invokeAny(set);
            System.out.println(s);
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void invokeAllDemo(){
        try {
            HashSet<Callable<String>> set = new HashSet<>();
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                set.add(() -> {
                    return Thread.currentThread().getName() + "---"+finalI;
                });
            }
            ExecutorService service = Executors.newCachedThreadPool();
            List<Future<String>> futures = service.invokeAll(set);
            for (Future<String> future : futures) {
                System.out.println("result "+future.get());
            }

            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class CallableDemo<T> implements Callable<T>{

    @Override
    public T call() throws Exception {
        /**
         * 在线程池之中存在拒绝策略的概念，所谓的拒绝策略指的是线程池满了之后的其它等待线程的处理状态，
         * 在ThreadPoolExecutor类里面提供有一些”RejectedExecutionHandler”子类，如果现在被拒绝了会出现”拒绝异常”
         * (默认AbortPolicy)。对于给出的几种拒绝策略如下：
         *    * AbortPolicy(默认实现)：当任务添加到线程池中被拒绝时，会抛出拒绝异常”RejectedExecutionException”;
         *    * DiscardPolicy：当将任务添加到线程池中被拒绝的时候，线程池将直接丢弃该拒绝的任务；
         *    * DiscardOldestPolicy:当被拒绝的时候，线程池会放弃等待队列中时间最长的未被处理的任务，
         *         让后将拒绝的任务添加到队列之中；
         *    * CallerRunsPolicy:当任务被拒绝时，会在线程池当前正在运行的Thread线程之中处理该任务(加塞儿)。
         */
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(2) ;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 5, 6L, TimeUnit.SECONDS,
                queue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        for (int x = 0 ; x < 2 ; x ++) {
            int temp = x ;
            poolExecutor.execute(()->{
                System.out.println("【"
                        +Thread.currentThread().getName()
                        +"】任务开始执行 - " + temp);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("【"
                        +Thread.currentThread().getName()
                        +"】任务结束执行 - " + temp);
            });
        }
        return null;
    }
}