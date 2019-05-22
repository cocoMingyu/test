package design_pattern.factory.factory;

import design_pattern.factory.abstract_factory.YuanLiaoFactory;
import design_pattern.factory.simple.SZB;

/**
 * @ Author:kn
 * @ Description:抽象工厂类，抽出相同的方法，定义抽象方法由各个子类继承，由各个子类单独实现
 * @ Date:Created in 2019/5/22 11:23
 * @ Modified By:
 */
public abstract class AbstractCreateFactory {
    public abstract SZB setSzb(String name);

    public final SZB sellSZB(String name){
        SZB szb = setSzb(name);
        if (szb != null) {
            szb.propare();
            szb.start();
            szb.end();
        }
        return szb;
    }
    public final SZB sellSZB(YuanLiaoFactory factory, String name,String peicainame, int weight){
        SZB szb = setSzb(name);
        if (szb != null) {
            szb.propare(factory, peicainame, weight);
            szb.start();
            szb.end();
        }
        return szb;
    }
}
