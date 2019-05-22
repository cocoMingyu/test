package design_pattern.factory.simple;

/**
 * @ Author:kn
 * @ Description:工厂类，根据不同的输入参数制作不同口味的手抓饼
 * @ Date:Created in 2019/5/22 10:25
 * @ Modified By:
 */
public class CreateFactory {
    public SZB getSZB(String name){
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

    public SZB sellSZB(String name){
        SZB szb = getSZB(name);
        if (szb != null) {
            szb.propare();
            szb.start();
            szb.end();
        }
        return szb;
    }
}
