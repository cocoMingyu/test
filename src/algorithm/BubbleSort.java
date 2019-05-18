package algorithm;

import java.util.Random;

/**
 * @ Author:kn
 * @ Description:
 * 1.如果我们的数据正序，只需要走一趟即可完成排序。所需的比较次数C和记录移动次数M均达到最小值，即：Cmin=n-1;Mmin=0;所以，冒泡排序最好的时间复杂度为O(n)。
 *2.如果很不幸我们的数据是反序的，则需要进行n-1趟排序。每趟排序要进行n-i次比较(1≤i≤n-1)，且每次比较都必须移动记录三次来达到交换记录位置。在这种情况下，比较和移动次数均达到最大值：冒泡排序的最坏时间复杂度为：O(n2) 。
 * 综上所述：冒泡排序总的平均时间复杂度为：O(n2) 。
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] ints = randomArr();
        int[] sortArr = sort(ints);
        System.out.println("数组长度"+sortArr.length);
        for (int i : sortArr) {
            System.out.println(i);
        }
    }
    public static int[] randomArr(){
        int[] ints = new int[50];
        for (int i = 0; i < ints.length; i++) {
            Random random = new Random();
            int num = random.nextInt(10000);
            ints[i]=num;
        }
        return ints;
    }

    public static int[] sort(int[] ints){
        for (int i = 0; i < ints.length; i++) {//控制排序次数
            for (int j = 1; j < ints.length-i; j++) {//控制每次排序排几次
                if (ints[j-1]>ints[j]){
                    int temp=ints[j-1];
                    ints[j-1]=ints[j];
                    ints[j]=temp;
                }
            }
        }
        return ints;
    }
}
