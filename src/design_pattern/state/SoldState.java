package design_pattern.state;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/7 20:09
 * @ Modified By:
 */
public class SoldState implements State {
    private VendingMachine machine;

    public SoldState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void addMoney() {
        System.out.println("出货中，请勿投币");
    }

    @Override
    public void backMoney() {
        System.out.println("出货中,无法退款");
    }

    @Override
    public void trunCrank() {
        System.out.println("正在出货，请勿重复转动手柄");
    }

    @Override
    public void dispense() {
        if (machine.getCount()>0){
            System.out.println("出货成功");
            machine.setCurrentStatus(machine.getNoMoneyState());
        }else {
            machine.setCurrentStatus(machine.getSoldOutState());
            System.out.println("商品售罄");
        }
    }
}
