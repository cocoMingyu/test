package design_pattern.temeplete;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/8 14:50
 * @ Modified By:
 */
public class ItWorker extends Worker {
    public ItWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("每天苦逼写业务代码");
    }

    @Override
    public boolean printDate() {
        return true;
    }
}
