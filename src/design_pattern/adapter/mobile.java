package design_pattern.adapter;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/20 11:51
 * @ Modified By:
 */
public class mobile {
    public void inputPower(V5Power v5Power){
        int i = v5Power.provideV5Power();
        System.out.println("手机需要5v充电器，现在是"+i+"v");
    }
}
