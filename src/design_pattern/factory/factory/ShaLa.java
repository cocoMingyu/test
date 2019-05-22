package design_pattern.factory.factory;

import design_pattern.factory.simple.SZB;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/22 12:23
 * @ Modified By:
 */
public class ShaLa extends SZB {
    public ShaLa(String name) {
        this.kouwei=name;
        System.out.println("这是"+name+"手抓饼");
    }
}
