package design_pattern.temeplete;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/8 14:55
 * @ Modified By:
 */
public class HrWorker extends Worker {
    public HrWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("每天面面人");
    }
}
