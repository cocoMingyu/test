package design_pattern.state;

/**
 * @ Author:kn
 * @ Description:可以看到，我们现在把每个状态对应于动作的行为局部化到了状态自己的类中实现，不仅增加了扩展性而且使代码的阅读性大幅度的提高。
 * 以后再添加状态，只需要针对新添加的状态的实现类，并在自动售货机中添加此状态即可。
 */
public class Test {
    public static void main(String[] args) {

        VendingMachine machine = new VendingMachine(10);
        machine.addMoney();
        machine.backMoney();

        System.out.println("----我要中奖----");
        for (int i = 0; i < 3; i++) {
            machine.addMoney();
            machine.trunCrank();
        }

        System.out.println("-------压力测试------");

        machine.addMoney();
        machine.backMoney();
        machine.backMoney();
        machine.trunCrank();// 无效操作
        machine.trunCrank();// 无效操作

    }
}
