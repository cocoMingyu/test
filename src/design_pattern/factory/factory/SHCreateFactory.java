package design_pattern.factory.factory;

import design_pattern.factory.simple.FanQie;
import design_pattern.factory.simple.Heihujiao;
import design_pattern.factory.simple.SZB;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/22 11:37
 * @ Modified By:
 */
public class SHCreateFactory extends AbstractCreateFactory {
    @Override
    public SZB setSzb(String name) {
        SZB szb=null;
        if (name.equals("黑胡椒")){
            szb=new Heihujiao(name);
        }else if(name.equals("番茄")){
            szb=new FanQie(name);
        }else {
            System.out.println("请输入已有的口味");
        }
        return szb;
    }
}
