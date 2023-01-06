package org.top;

//https://leetcode-cn.com/problems/rotate-image
//旋转图像
public class rotate_image {
    public static void main(String[] args) {
        int[][] arr= new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};

//        int[][] arr= new int[][]{{5,1,9,11}, {2,4,8,10}, {13,3,6,7}, {15,14,12,16}};

        rotate(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.println(arr[i][j]);
            }
        }
    }

    public static void rotate(int[][] matrix) {
        int time = 0;
        while (time < matrix.length / 2) {
            // 根据圈的次数计算圈的边长
            int len = matrix.length - 2 * time;

            for (int i = 0; i < len - 1; i++) {
                int tmp = matrix[time][time + i];
                matrix[time][time + i] = matrix[time + len - 1 - i][time];
                matrix[time + len - 1 - i][time] = matrix[time + len - 1][time + len - 1 - i];
                matrix[time + len - 1][time + len - 1 - i] = matrix[time + i][time + len - 1];
                matrix[time + i][time + len - 1] = tmp;
            }

            time++;
        }
    }
}
