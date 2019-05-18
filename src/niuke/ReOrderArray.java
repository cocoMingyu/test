package niuke;

/**
 * @ Author:kn
 * @ Description:输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * @ Date:Created in 2019/5/16 18:51
 * @ Modified By:
 */
public class ReOrderArray {
    public static void main(String[] args) {
        int[] a={1,2,3,4,5,6,7,8,9,10};
        reOrderArray(a);
        for (int i : a) {
            System.out.print(i+" ");
        }
    }
    public static void reOrderArray(int [] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length-1; j >i; j--) {
                if (array[j-1]%2==0&&array[j]%2!=0){
                    int temp=array[j-1];
                    array[j-1]=array[j];
                    array[j]=temp;
                }

            }
        }
    }
}
