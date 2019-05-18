package test;

import java.util.Arrays;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/10 18:20
 * @ Modified By:
 */
public class PrintArray {
    public static void main(String[] args) {
        int[][] arr = getArr(7);
        int start=0;
        int end=arr.length-1;
        for (int i=0;i<arr.length;i++) {
            int[] startarr = arr[start];
            int[] endarr = arr[end];
            for (int j = 0; j < i; j++) {
                System.out.print("\t");
            }
            for (int i1=endarr.length-1;i1>=0;i1--) {
                    System.out.print(endarr[i1]+"\t");
            }
            for (int i1 : startarr) {
                    System.out.print(i1+"\t");
            }
            System.out.println();
            start++;
            end--;
        }
    }
    public static int[][] getArr(int number){
        int num=number;
        if (num%2==0){
            num=num/2;
        }else {
            num=num/2+1;
        }
        int[][] doubleArr=new int[num][];
        int count=0;
        for (int i = 1; i <= number; i+=2) {
            doubleArr[count]=new int[count+1];
            for (int j = 0; j <count+1 ; j++) {
                if (j==0){
                    doubleArr[count][j]=i;
                }else {
                    doubleArr[count][j]=doubleArr[count][j-1]+doubleArr[count-1][j-1];
                }
            }
            count++;
        }
        return doubleArr;
    }

}
