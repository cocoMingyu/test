package design_pattern.temeplete;

import java.util.Date;

/**
 * @ Author:kn
 * @ Description:超类，超类中定义了一个workOneDay方法，设置为作为算法的骨架
 * 抽象类无法实例化 --必须由子类实现抽象方法
 */
public abstract class Worker {
    protected String name;

    public Worker(String name) {
        this.name = name;
    }

    /**
     * 记录一天的工作
     */
    public final void workOneDay(){
        System.out.println("-------------工作开始------------");
        enterCompany();
        computerOn();
        work();
        computerOff();
        exitCompany();
        System.out.println("-------------工作结束------------");
    }
    public abstract void work();

    /**
     *钩子函数：超类中添加了一个PrintDate方法，且默认返回false，不打印时间。如果某子类需要调用打印时间，可以复写改钩子方法，返回true，比如，程序猿复写了这个方法
     * 关于钩子，超类中可提供默认实现或者空实现，子类可覆盖或者不覆盖，具体根据需求来定。
     */
    public boolean printDate(){
        return false;
    }
    private void enterCompany(){
        System.out.println(this.name+"进入公司");
    }
    private void computerOn(){
        System.out.println(this.name+"打开电脑");
    }
    private void computerOff(){
        System.out.println(this.name+"关闭电脑");
    }
    private void exitCompany(){
        if (printDate()){
            System.out.println(new Date().toLocaleString()+">>>");
        }
        System.out.println(this.name+"离开公司");
    }
}
