package design_pattern.factory.factory;

import design_pattern.factory.simple.FanQie;
import design_pattern.factory.simple.Heihujiao;
import design_pattern.factory.simple.SZB;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/22 12:25
 * @ Modified By:
 */
public class CCCreateFactory extends AbstractCreateFactory {
    @Override
    public SZB setSzb(String name) {
        SZB szb=null;
        if (name.equals("沙拉")){
            szb=new Heihujiao(name);
        }else if(name.equals("孜然")){
            szb=new FanQie(name);
        }else {
            System.out.println("请输入已有的口味");
        }
        return szb;
    }
}
