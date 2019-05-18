package juc.queue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ Author:kn
 * @ Description:【 延迟队列实现数据缓存 】
 * 在延迟队列之中有一个最大的特征就是到点后进行清除处理，那么在实际的开发之中，可以利用此机制来实现一个缓存的处理操作。
 * 正规的开发之中，往往都会利用数据层通过数据库进行数据的获得，并且将这些数据变为VO的形式。
 */
public class DelayQueueDemo_Cache {
    public static void main(String[] args) throws Exception {
        Cache<String,News> cache = new Cache<String,News>() ;
        cache.put("小岳岳",new News("小岳岳","跑路了"),3,TimeUnit.SECONDS) ;
        cache.put("小疯冯",new News("小疯冯","吃屎了 "),3,TimeUnit.SECONDS) ;
        System.out.println(cache.get("小岳岳"));
        TimeUnit.SECONDS.sleep(5);
        System.out.println(cache.get("小岳岳"));
        System.out.println(cache.get("小疯冯"));
    }
}
//定义缓存操作类
class Cache<K,V>{
    private ConcurrentHashMap<K,V> concurrentHashMap=new ConcurrentHashMap<>();
    private DelayQueue<DaleyItem<Pair>> pairDaleyItem=new DelayQueue<>();
    private class Pair{//定义一个内部类,可以保存队列之中的k,v值
        private K key;
        private V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    //如果要想清空不需要的缓冲数据,需要守护线程
    public Cache(){
        Runnable daemonTask=()->{
            //守护线程要一直执行,超时后可以取出数据
            while (true){
                DaleyItem<Pair> item = Cache.this.pairDaleyItem.poll();
                //有数据超时了
                if (item!=null){
                    Pair pair = item.getItem();
                    Cache.this.concurrentHashMap.remove(pair.key,pair.value);
                }
            }
        };
        Thread thread = new Thread(daemonTask, "缓存守护线程");
        //设置守护线程
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 表示将要保存的数据写入缓存,如果对象重复保存了,重置超时时间
     * @param key
     * @param value
     * @param time 保存时间
     * @param timeUnit 时间单位
     */
    public void put(K key,V value,long time,TimeUnit timeUnit){
        //put方法key如果存在,新的value替换旧的,返回旧的value
        V oldValue = this.concurrentHashMap.put(key, value);
        //数据已经保存过了
        if (oldValue!=null){
            this.pairDaleyItem.remove(key);
        }
        this.pairDaleyItem.put(new DaleyItem<Pair>(new Pair(key, value),time,timeUnit));
    }

    public V get(K key){
        return this.concurrentHashMap.get(key);
    }
}
class DaleyItem<T> implements Delayed{
    private T item;//设置要保存的数据内容
    private long delay;//保存的缓存时间
    private long expire;//缓存的过期时间

    public DaleyItem(T item, long delay,TimeUnit timeUnit) {
        this.item = item;
        this.delay = TimeUnit.MILLISECONDS.convert(delay,timeUnit);
        this.expire=System.currentTimeMillis()+this.delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {//计算延迟时间是否到达
        return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {//出栈顺序
        return (int)(this.delay-getDelay(TimeUnit.MILLISECONDS));
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
class News {   // 定义一个新闻类
    private String title ;
    private String note ;
    public News(String title,String note) {
        this.title = title ;
        this.note = note ;
    }
    public String toString() {
        return "【新闻数据】title = " + this.title
                + "、note = " + this.note ;
    }
}