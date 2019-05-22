package design_pattern.factory.abstract_factory;

import design_pattern.factory.abstract_factory.peicai.PeiCai;

/**
 * @ Author:kn
 * @ Description:提供一个抽象工厂接口用来创建一个产品家族，因为需要创建一个产品家族（原料），把该类实现为抽象工厂，每个子类都使用其区域的供货商实现原料
 * @ Date:Created in 2019/5/22 16:33
 * @ Modified By:
 */
public interface YuanLiaoFactory{
    PeiCai peicai(String name, int weight);
}
