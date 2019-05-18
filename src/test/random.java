package test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:lkn
 * @Description:
 * @Date:Created in 2018/10/20 14:40
 * @Modified By:
 */
public class random {
    public static void main(String[] args) {
        String[] arr={"aaaaaa","bbbbbbb","ccccccc","ddddddd"};
        for (int i = 0; i < 50; i++) {
            int i1 = (int) (3 + Math.random() * 2);
            System.out.println(i1);
        }


    }
}
