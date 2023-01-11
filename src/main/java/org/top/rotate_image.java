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


    public static void rotate1(int[][] matrix) {
        int i1 = 0, j1 = 0;
        int i2 = 0, j2 = matrix[i2].length - 1;
        int i3 = matrix.length - 1, j3 = 0;
        int i4 = matrix.length - 1, j4 = matrix[i4].length - 1;

        while (i1 < i4 && j1 < j4) {

            int tmp_i1 = i1, tmp_j1 = j1;
            int tmp_i2 = i2, tmp_j2 = j2;
            int tmp_i3 = i3, tmp_j3 = j3;
            int tmp_i4 = i4, tmp_j4 = j4;

            while (tmp_j1 < tmp_j2) {

                int tmp = matrix[tmp_i1][tmp_j1];
                matrix[tmp_i1][tmp_j1] = matrix[tmp_i3][tmp_j3];
                matrix[tmp_i3][tmp_j3] = matrix[tmp_i4][tmp_j4];
                matrix[tmp_i4][tmp_j4] = matrix[tmp_i2][tmp_j2];
                matrix[tmp_i2][tmp_j2] = tmp;

                tmp_j1++;
                tmp_i2++;
                tmp_i3--;
                tmp_j4--;

            }

            i1++;
            j1++;
            i2++;
            j2--;
            i3--;
            j3++;
            i4--;
            j4--;

        }

    }
}
