package design_pattern.factory.abstract_factory;

import design_pattern.factory.factory.CCCreateFactory;

/**
 * @ Author:kn
 * @ Description:抽象工厂模式：提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类
 * 抽象工厂使用对象组合：对象的创建被实现在工厂接口所暴露的方法中
 *
 * 抽象工厂的方法通常以工厂方法的方式实现，抽象工厂的任务是定义一组产品的接口，这个接口的每个方法都负责创建一个具体产品，
 * 同时利用实现抽象工厂的子类来实现这些具体的方法。
 * @ Date:Created in 2019/5/22 18:05
 * @ Modified By:
 */
public class Test {
    public static void main(String[] args) {
        CCCreateFactory ccCreateFactory=new CCCreateFactory();
        ccCreateFactory.sellSZB(new YuanLiaoImpl(),"沙拉","香肠",100);
    }
}
