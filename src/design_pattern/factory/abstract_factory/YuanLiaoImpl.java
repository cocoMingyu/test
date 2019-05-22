package design_pattern.factory.abstract_factory;

import design_pattern.factory.abstract_factory.peicai.CCPeiCaiFactory;
import design_pattern.factory.abstract_factory.peicai.PeiCai;
import design_pattern.factory.abstract_factory.peicai.PeiCaiAbstractFactory;

/**
 * @ Author:kn
 * @ Description:每个具体子类都创建一个家族产品，这些负责在抽象工厂创建产品的方法，通常是以“工厂方法”实现
 * @ Date:Created in 2019/5/22 17:58
 * @ Modified By:
 */
public class YuanLiaoImpl implements YuanLiaoFactory{
    @Override
    public PeiCai peicai(String name, int weight) {
        //由配菜工厂方法实现
        CCPeiCaiFactory ccPeiCaiFactory = new CCPeiCaiFactory();
        PeiCai xc = ccPeiCaiFactory.usePeiCai(name, weight);
        return xc;
    }
}
