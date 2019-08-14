package test;


import java.util.*;


public class test1 {
    public int letterToNumber(String letter) {
        int length = letter.length();
        int num = 0;
        int number = 0;
        for (int i = 0; i < length; i++) {
            System.out.println(i);
            char ch = letter.charAt(length - i - 1);
            System.out.println(ch);
            num = (int) (ch - 'A');
            System.out.println(num);
            num *= Math.pow(26, i);
            number += num;
        }
        return number;
    }


    public static void main(String[] args) {
        String abc = System.getProperty("abc");
        if (abc==null){
            System.out.println("is null");
        }else {
            System.out.println(abc);
        }
    }
}
