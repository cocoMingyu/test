package design_pattern.facade;

/**
 * @ Author:kn
 * @ Description:外观模式（Facade Pattern）定义：提供一个统一的接口，用来访问子系统中的一群接口，外观定义了一个高层的接口，让子系统更容易使用。其实就是为了方便客户的使用，把一群操作，封装成一个方法。
 * @ Date:Created in 2019/5/20 11:42
 * @ Modified By:
 */
public class Test {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.oneDay();
    }
}
