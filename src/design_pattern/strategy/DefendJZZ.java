package design_pattern.strategy;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/8 11:02
 * @ Modified By:
 */
public class DefendJZZ implements DefendBehavior{
    @Override
    public void defend() {
        System.out.println("金钟罩");
    }
}
