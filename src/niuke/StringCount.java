package niuke;

import java.util.Arrays;

/**
 * @ Author:kn
 * @ Description:请实现一个算法，确定一个字符串的所有字符是否全都不同。这里我们要求不允许使用额外的存储结构。
 * 给定一个string iniString，请返回一个bool值,True代表所有字符全都不同，False代表存在相同的字符。保证字符串中的字符为ASCII字符。字符串的长度小于等于3000。
 */
public class StringCount {
    public static void main(String[] args) {
        String reverse = blankChange(" aa b",5);
        System.out.println(reverse);
    }

    public static boolean stringCount(String iniString){
        return !iniString.matches(".*(.)(.*\\1).*");
    }

    public static String reverse(String iniString){
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < iniString.length(); i++) {
            stringBuffer.append(iniString.charAt(i));
        }
        return stringBuffer.toString();
    }

    public static boolean compare(String stringA,String stringB){
        char[] chara = stringA.toCharArray();
        Arrays.sort(chara);
        char[] charb = stringB.toCharArray();
        Arrays.sort(charb);
        return Arrays.equals(charb,chara);
    }

    public static String blankChange(String iniString, int length){
        return iniString.replaceAll(" ","%20");
    }
}
