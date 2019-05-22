package design_pattern.factory.abstract_factory.peicai;

/**
 * @ Author:kn
 * @ Description:配菜抽象类
 * @ Date:Created in 2019/5/22 16:44
 * @ Modified By:
 */
public class PeiCai {
    public String name;
    public int weight ;

    public void getPeiCai(){
        System.out.println("选取配菜："+name);
    }
    public void addPeiCai(){
        System.out.println("添加配菜"+name+weight+"克");
    }
}
