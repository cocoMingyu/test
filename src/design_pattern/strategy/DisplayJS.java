package design_pattern.strategy;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/8 11:19
 * @ Modified By:
 */
public class DisplayJS implements DisplayBehavior{
    @Override
    public void display() {
        System.out.println("丑比");
    }
}
