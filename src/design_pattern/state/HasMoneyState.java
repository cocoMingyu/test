package design_pattern.state;

/**
 * @ Author:kn
 * @ Description:有钱的状态
 */
public class HasMoneyState implements State {
    private VendingMachine vendingMachine;

    public HasMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void addMoney() {
        System.out.println("已经投过币了，无需再投");
    }

    @Override
    public void backMoney() {
        vendingMachine.setCurrentStatus(vendingMachine.getNoMoneyState());
        System.out.println("退币成功");
    }

    @Override
    public void trunCrank() {
        System.out.println("您转动了手柄");
        if (vendingMachine.getCount()>0){
            vendingMachine.setCurrentStatus(vendingMachine.getSoldState());
        }else {
            vendingMachine.setCurrentStatus(vendingMachine.getSoldOutState());
        }

    }

    @Override
    public void dispense() {
        throw  new IllegalStateException("非法状态");
    }
}
