package design_pattern.adapter;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/20 11:57
 * @ Modified By:
 */
public class AdapterVPower implements V5Power {
    private V220Power v220Power;

    public AdapterVPower(V220Power v220Power) {
        this.v220Power = v220Power;
    }

    @Override
    public int provideV5Power() {
        int i = v220Power.provideV220();
        int j=i;
        j=5;
        System.out.println("经过操作将"+i+"转换成"+j+"v");
        return j;
    }
}
