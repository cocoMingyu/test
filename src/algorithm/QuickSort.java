package algorithm;

import java.util.Random;

/**
 * @ Author:kn
 * @ Description:选择一个关键值作为基准值，比基准值小的在左边序列（无序），大的在右边序列（无序）
 * 一次循环：从后向前比较，
 * @ Date:Created in 2019/5/9 18:26
 * @ Modified By:
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] ints = randomArr();
        System.out.println("基准值："+ints[0]);
        int[] arr = sort(ints,0,ints.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }
    public static int[] sort(int[] arr,int light,int right){
        int l=light;
        int r=right;
        int x=arr[l];
        while (l<r){
            while (l<r&&arr[r]>=x){//从右向左找比x 小的值
                r--;
            }
            if (l<r&&arr[r]<=x){
                int temp=arr[r];
                arr[r]=arr[l];
                arr[l]=temp;
            }

            while (l<r&&arr[l]<=x){
                l++;
            }
            if (l<r&&arr[l]>=x){
                int temp=arr[l];
                arr[l]=arr[r];
                arr[r]=temp;
            }

        }
        if (l>light)

        sort(arr,light,l-1);
        if (r<right)
        sort(arr,l+1,right);
        return arr;
    }

    public static int[] randomArr(){
        int[] ints = new int[8];
        for (int i = 0; i < ints.length; i++) {
            Random random = new Random();
            int num = random.nextInt(100);
            ints[i]=num;
        }
        return ints;
    }
}
