package niuke;

import java.util.Arrays;
import java.util.Collections;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/12 14:42
 * @ Modified By:
 */
public class MinNumberInRotateArray {
    public static void main(String[] args) {
        int i = minNumberInRotateArray(3);
        System.out.println(i);
    }

    public static int minNumberInRotateArray(int n) {
        int a=0;int b=1;int c=1;
        if (n<1){
            return 0;
        }
        if (n==1){
            return 1;
        }
        for (int i=2;i<=n;i++){
            c=a+b;
            a=b;
            b=c;
        }
        return c;
    }


}
