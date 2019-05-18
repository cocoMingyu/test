package design_pattern.state;

/**
 * @ Author:kn
 * @ Description:没钱的状态
 */
public class NoMoneyState implements State {
    private VendingMachine vendingMachine;

    public NoMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void addMoney() {
        vendingMachine.setCurrentStatus(vendingMachine.getHasMoneyState());
        System.out.println("投币成功");
    }

    @Override
    public void backMoney() {
        System.out.println("没投币还想退钱？");
    }

    @Override
    public void trunCrank() {
        System.out.println("没投币还想拿东西？");
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("非法状态");
    }
}
