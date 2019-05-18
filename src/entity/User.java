package entity;

import java.math.BigDecimal;

/**
 * @Author:lkn
 * @Description:
 * @Date:Created in 2019/1/15 10:52
 * @Modified By:
 */
public class User implements Cloneable {
    private String name;
    private Integer age;
    private double money;
    private BigDecimal hright;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age, double money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public User(String name, Integer age, double money, BigDecimal hright) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.hright = hright;
    }

    public BigDecimal getHright() {
        return hright;
    }

    public void setHright(BigDecimal hright) {
        this.hright = hright;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public User clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        return clone;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + ", money=" + money + '}';
    }
}
