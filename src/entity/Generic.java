package entity;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/2/27 10:51
 * @ Modified By:
 */

public class Generic<T extends Number> {
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
