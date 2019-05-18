package shangguigu_mianshi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ Author:kn
 * @ Description: 1.校验volatile可见性, num没有添加volatile修饰,不具有可见性
 *                2.volatile不保证原子性:不可分割,完整性,需要整体完整,要么成功,要么失败
 * @ Date:Created in 2019/4/24 17:29
 * @ Modified By:
 */
public class Volitale {

    public static void main(String[] args) {
        long convert = TimeUnit.SECONDS.convert(1, TimeUnit.HOURS);
        System.out.println(convert);
        AtomicInteger atomicInteger = new AtomicInteger(100);
        boolean b = atomicInteger.compareAndSet(100, 200);
        System.out.println(b);
        System.out.println(atomicInteger);
    }
    //volatile保证可见性,及时通知其他线程,主物理内存被修改
    public static void seeOkByVolatile(){
        Data data = new Data();//资源类
        new Thread(() ->{//第一个线程
            System.out.println(Thread.currentThread().getName()+"  come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.change(data);
            System.out.println(Thread.currentThread().getName()+"num="+data.num);
        },"first thread").start();

        while (data.num==10){//第二个线程 main线程
            //值没有变,线程一直等待循环
        }
        System.out.println("num  change  value="+data.num);
    }
}
class Data{
    volatile int num=0;
    public void change(Data myData){
        myData.num=20;
    }
    public void add(){
        num++;
    }
}
