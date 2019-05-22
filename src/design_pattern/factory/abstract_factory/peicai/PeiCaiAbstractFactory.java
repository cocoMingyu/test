package design_pattern.factory.abstract_factory.peicai;

/**
 * @ Author:kn
 * @ Description: 抽象工厂包含的工厂方法
 * @ Date:Created in 2019/5/22 16:35
 * @ Modified By:
 */
public abstract class PeiCaiAbstractFactory {
    public abstract PeiCai setPeiCai(String name,int weight);

    public PeiCai usePeiCai(String name, int weight){
        PeiCai peiCai = setPeiCai(name,weight);
        if (peiCai != null) {
            peiCai.getPeiCai();
            peiCai.addPeiCai();
        }
        return peiCai;
    }
}
