package design_pattern.strategy;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/8 11:03
 * @ Modified By:
 */
public class Role {
    protected String name;
    private AttackBehavior attackBehavior;
    private DefendBehavior defendBehavior;
    private DisplayBehavior displayBehavior;
    private RunBehavior runBehavior;

    public Role(String name) {
        this.name = name;
    }

    public void attack(){
        attackBehavior.sttack();
    }
    public void defend(){
        defendBehavior.defend();
    }
    public void run(){
        runBehavior.run();
    }
    public void display(){
        displayBehavior.display();
    }


    public AttackBehavior getAttackBehavior() {
        return attackBehavior;
    }

    public void setAttackBehavior(AttackBehavior attackBehavior) {
        this.attackBehavior = attackBehavior;
    }

    public DefendBehavior getDefendBehavior() {
        return defendBehavior;
    }

    public void setDefendBehavior(DefendBehavior defendBehavior) {
        this.defendBehavior = defendBehavior;
    }

    public DisplayBehavior getDisplayBehavior() {
        return displayBehavior;
    }

    public void setDisplayBehavior(DisplayBehavior displayBehavior) {
        this.displayBehavior = displayBehavior;
    }

    public RunBehavior getRunBehavior() {
        return runBehavior;
    }

    public void setRunBehavior(RunBehavior runBehavior) {
        this.runBehavior = runBehavior;
    }
}
