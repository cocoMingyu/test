package niuke;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:kn
 * @ Description:输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class PrintMatrix {
    public static void main(String[] args) {
        int[][] a={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        ArrayList<Integer> integers = printMatrix(a);
        integers.stream().forEach(b -> System.out.print(b+" "));
    }
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list=new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        if (row==0||col==0){
            return list;
        }
        //定义四个变量，表示左上右下的打印范围
        int left=0;int top=0;int right=col-1;int bottom=row-1;
        while (left<=right&&top<=bottom){
            //从左到右
            for (int i = left; i <= right; i++) {
                int num = matrix[top][i];
                list.add(num);
            }
            //从上到下
            for (int i = top+1; i <= bottom; i++) {
                int num = matrix[i][right];
                list.add(num);
            }
            //从右到左
            for (int i = right-1; i>=left; i--) {
                int num = matrix[bottom][i];
                list.add(num);
            }
            //从下到上
            for (int i = bottom-1; i >top; i--) {
                int num = matrix[i][left];
                list.add(num);
            }
            top++;left++;right--;bottom--;
        }
        return list;
    }
}
