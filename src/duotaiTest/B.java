package duotaiTest;

/**
 * @Author:lkn
 * @Description:
 * @Date:Created in 2019/1/18 11:22
 * @Modified By:
 */
public class B extends A{
    public String show(B obj){
        return ("B and B");
    }

    public String show(A obj){
        return ("B and A");
    }
}
