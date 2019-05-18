package juc.queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ Author:kn
 * @ Description:现在学生考试里面可以有许多的学生参加考试，且老师需要进行学生考试的监督，
 *          每一个学生的考试时间都是不同的，但是不管你多不同，一旦到点之后一定要结束考试。
 */
public class DelayQueueDemo2 {
    public static void main(String[] args) {
        Random random=new Random();
        DelayQueue<student> delayQueue=new DelayQueue<>();
        for (int i = 0; i < 2; i++) {
            int testTime = random.nextInt(20);
            student stu = new student("考生" + i, testTime, TimeUnit.SECONDS);
            delayQueue.put(stu);
        }
        new Thread(new teacher(2,delayQueue)).start();


    }

}
class teacher implements Runnable{
    //交卷数量
    private int submitcount=0;
    //考试学生数量
    private int studengcount=0;
    DelayQueue<student> students;

    public teacher( int studengcount, DelayQueue<student> students) {
        this.studengcount = studengcount;
        this.students = students;
    }

    @Override
    public void run() {
        System.out.println("-----------------开始考试----------------");
        try {
            while (submitcount<studengcount){
                //有人出队列,表示有人交卷
                student stu = this.students.poll();
                if (stu!=null){
                    stu.exc();//交卷处理
                    this.submitcount++;//交卷人数加一
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-----------------考试结束----------------");
    }
}
class student implements Delayed{
    private String name;
    //交卷时间,毫秒
    private long submittime;
    //考试时间,毫秒
    private long testtime;

    public student(String name, long testtime,TimeUnit timeUnit) {
        this.name = name;
        this.testtime = TimeUnit.MILLISECONDS.convert(testtime,timeUnit);
        this.submittime = System.currentTimeMillis()+this.testtime;
    }
    public void exc(){//考试处理
        System.out.println(this.name+"交卷-"+"交卷时间-"+this.submittime+"耗时-"+this.testtime);
    }

    @Override
    public long getDelay(TimeUnit unit) {// 计算延迟时间是否到达
        long convert = unit.convert(this.submittime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        return convert;
    }

    @Override
    public int compareTo(Delayed o) {//决定了你优先级队列的弹出操作
        int i = (int) (this.testtime - this.getDelay(TimeUnit.MILLISECONDS));
        return i;
    }
}
