package design_pattern.factory.simple;

/**
 * @ Author:kn
 * @ Description:手抓饼 父类，子类为各种口味的手抓饼
 * @ Date:Created in 2019/5/21 18:17
 * @ Modified By:
 */
public class SZB {
    public String kouwei;

    public void propare(){
        System.out.println("手抓饼准备工作");
    }
    public void start(){
        System.out.println("开始做了");
    }
    public void end(){
        System.out.println("做完了，请吃");
    }
}
