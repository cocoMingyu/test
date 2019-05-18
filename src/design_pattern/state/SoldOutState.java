package design_pattern.state;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/7 20:08
 * @ Modified By:
 */
public class SoldOutState implements State {
    private VendingMachine vendingMachine;

    public SoldOutState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void addMoney() {
        System.out.println("商品售罄，投币失败");
    }

    @Override
    public void backMoney() {
        System.out.println("商品售罄，退币失败");
    }

    @Override
    public void trunCrank() {
        System.out.println("商品售罄，转也没有用");
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("非法操作");
    }
}
