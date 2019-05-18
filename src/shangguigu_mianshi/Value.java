package shangguigu_mianshi;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/4/24 9:43
 * @ Modified By:
 */
public class Value {

    public static void change(int j,String s,test test){
        j=2;
        s+="world";
        test.a=20;
    }

    public static void main(String[] args) {
        int i=1;
        String str="hello";
        test test = new test();
        change(i,str,test);
        System.out.println(i);
        System.out.println(str);
        System.out.println(test.a);
    }

}
class test{
    int a=10;
}
