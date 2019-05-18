package design_pattern.strategy;

/**
 * @ Author:kn
 * @ Description:策略模式（Strategy Pattern）：定义了算法族，分别封装起来，让它们之间可相互替换，此模式让算法的变化独立于使用算法的客户。
 * 总结一下OO的原则：
 * 1、封装变化（把可能变化的代码封装起来）
 * 2、多用组合，少用继承（我们使用组合的方式，为客户设置了算法）
 * 3、针对接口编程，不针对实现（对于Role类的设计完全的针对角色，和技能的实现没有关系）
 */
public class Test {
    public static void main(String[] args) {
        Role role = new Role("张三");
        role.setAttackBehavior(new AttackJY());
        role.setDefendBehavior(new DefendJZZ());
        role.setDisplayBehavior(new DisplayJS());
        role.setRunBehavior(new RunLBWB());

        System.out.println(role.name);
        role.attack();
        role.defend();
        role.display();
        role.run();
    }
}
