package design_pattern.factory.abstract_factory.peicai;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/22 17:33
 * @ Modified By:
 */
public class CCPeiCaiFactory extends PeiCaiAbstractFactory {
    @Override
    public PeiCai setPeiCai(String name,int weight) {
        PeiCai peiCai=null;
        if (name.equals("香肠")){
            peiCai=new XiangChang(name,weight);
        }else if (name.equals("鱼丸")){
            peiCai=new YuWan(name,weight);
        }else {
            System.out.println("输入正确的配菜");
        }
        return peiCai;
    }
}
