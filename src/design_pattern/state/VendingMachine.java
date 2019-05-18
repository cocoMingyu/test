package design_pattern.state;

/**
 * @ Author:kn
 * @ Description:自动售货机
 */
public class VendingMachine {
    private NoMoneyState noMoneyState;
    private HasMoneyState hasMoneyState;
    private SoldState soldState;
    private SoldOutState soldOutState;
    private int count=0;
    private State currentStatus=noMoneyState;

    public VendingMachine(int count) {
        this.hasMoneyState=new HasMoneyState(this);
        this.noMoneyState=new NoMoneyState(this);
        this.soldState=new SoldState(this);
        this.soldOutState=new SoldOutState(this);
        if (count>0){
            this.count=count;
            currentStatus=noMoneyState;
        }
    }

    public void addMoney(){
        currentStatus.addMoney();
    }

    public void backMoney(){
        currentStatus.backMoney();
    }

    public void trunCrank(){
        currentStatus.trunCrank();
        if (currentStatus==soldState){
            dispense();
        }
    }

    public void dispense(){
        currentStatus.dispense();
        --count;
    }

    public State getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(State currentStatus) {
        this.currentStatus = currentStatus;
    }

    public NoMoneyState getNoMoneyState() {
        return noMoneyState;
    }

    public void setNoMoneyState(NoMoneyState noMoneyState) {
        this.noMoneyState = noMoneyState;
    }

    public HasMoneyState getHasMoneyState() {
        return hasMoneyState;
    }

    public void setHasMoneyState(HasMoneyState hasMoneyState) {
        this.hasMoneyState = hasMoneyState;
    }

    public SoldState getSoldState() {
        return soldState;
    }

    public void setSoldState(SoldState soldState) {
        this.soldState = soldState;
    }

    public SoldOutState getSoldOutState() {
        return soldOutState;
    }

    public void setSoldOutState(SoldOutState soldOutState) {
        this.soldOutState = soldOutState;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
