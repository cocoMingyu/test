package design_pattern.adapter;

/**
 * @ Author:kn
 * @ Description:适配器模式：将一个类的接口转换成客户期望的另一个接口，适配器让原本接口不兼容的类可以相互合作。这个定义还好，说适配器的功能就是把一个接口转成另一个接口。
 * @ Date:Created in 2019/5/20 12:02
 * @ Modified By:
 */
public class Test {
    public static void main(String[] args) {
        mobile mobile = new mobile();
        V5Power power = new AdapterVPower(new V220Power());
        mobile.inputPower(power);
    }
}
