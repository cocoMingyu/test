package design_pattern.facade;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/20 11:32
 * @ Modified By:
 */
public class Facade {

    public void oneDay(){
        GoWork goWork = new GoWork();
        Working working = new Working();
        goWork.gotoWork();
        working.working();
    }
}
