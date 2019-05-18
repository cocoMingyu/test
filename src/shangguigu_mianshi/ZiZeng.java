package shangguigu_mianshi;
/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/4/16 18:01
 * @ Modified By:
 */
public class ZiZeng {
    public static void main(String[] args) {
        int i=1;
        i=i++;
        int j=i++;
        int k=i+ ++i*i++;
        System.out.println("i="+i);
        System.out.println("j="+j);
        System.out.println("k="+k);
    }
}
