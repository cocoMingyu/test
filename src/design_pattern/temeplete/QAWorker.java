package design_pattern.temeplete;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/8 14:56
 * @ Modified By:
 */
public class QAWorker extends Worker {
    public QAWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println("每天玩玩手机，发发呆");
    }
}
