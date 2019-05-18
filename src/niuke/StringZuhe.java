package niuke;

import java.util.ArrayList;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/16 23:57
 * @ Modified By:
 */
public class StringZuhe {
    public static void main(String[] args) {
    }
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> list=new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            StringBuilder sb=new StringBuilder().append(chars[i]);
            for (int j = 0; j < chars.length; j++) {
                if (i!=j){
                    sb.append(chars[j]);
                    list.add(sb.toString());
                }
            }
        }
        return list;
    }
}
