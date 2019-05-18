package design_pattern.state;

/**
 * @ Author:kn
 * @ Description:策略模式（Strategy Pattern）：定义了算法族，分别封装起来，让它们之间可相互替换，此模式让算法的变化独立于使用算法的客户。
 */
//状态的接口
public interface State {
    //放钱
    public void addMoney();
    //退钱
    public void backMoney();
    //转动手柄
    public void trunCrank();
    //出商品
    public void dispense();

}
