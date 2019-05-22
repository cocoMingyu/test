package design_pattern.factory.simple;

/**
 * @ Author:kn
 * @ Description: 手抓饼点，组合模式，组合工厂
 * @ Date:Created in 2019/5/22 10:29
 * @ Modified By:
 */
public class SZBstore {
    private CreateFactory createFactory=new CreateFactory();

    public SZBstore(CreateFactory createFactory) {
        this.createFactory = createFactory;
    }

    /**
     * 工厂根据参数制作手抓饼
     * @param name
     * @return
     */
    public SZB sellSZB(String name){
        SZB szb = createFactory.getSZB(name);
        if (szb != null) {
            szb.propare();
            szb.start();
            szb.end();
        }
        return szb;
    }
}
