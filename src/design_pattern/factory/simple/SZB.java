package design_pattern.factory.simple;

import design_pattern.factory.abstract_factory.YuanLiaoFactory;

/**
 * @ Author:kn
 * @ Description:手抓饼 父类，子类为各种口味的手抓饼
 * @ Date:Created in 2019/5/21 18:17
 * @ Modified By:
 */
public class SZB {
    public String kouwei;

    public void propare(){
        System.out.println(kouwei+"手抓饼准备工作");
    }
    public void propare(YuanLiaoFactory factory,String name,int weight){
        System.out.println(kouwei+"手抓饼准备工作");
        factory.peicai(name, weight);
    }
    public void start(){
        System.out.println(kouwei+"开始做了");
    }
    public void end(){
        System.out.println(kouwei+"做完了，请吃");
    }
}
