package design_pattern.strategy;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/8 11:03
 * @ Modified By:
 */
public class RunLBWB implements RunBehavior {
    @Override
    public void run() {
        System.out.println("凌波微步");
    }
}
