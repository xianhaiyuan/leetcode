package org.top.数字处理;

//https://leetcode-cn.com/problems/rotate-image
//旋转图像
/*
给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]

输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

提示：

    n == matrix.length == matrix[i].length
    1 <= n <= 20
    -1000 <= matrix[i][j] <= 1000
 */
public class rotate_image_3 {
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
        int i3 = matrix.length - 1, j3 = matrix[i3].length - 1;
        int i4 = matrix.length - 1, j4 = 0;

        while (i1 < i3 && j1 < j3) {
            int t_i1 = i1, t_j1 = j1;
            int t_i2 = i2, t_j2 = j2;
            int t_i3 = i3, t_j3 = j3;
            int t_i4 = i4, t_j4 = j4;

            while (t_j1 < t_j2) {
                int tmp = matrix[t_i1][t_j1];
                matrix[t_i1][t_j1] = matrix[t_i4][t_j4];
                matrix[t_i4][t_j4] = matrix[t_i3][t_j3];
                matrix[t_i3][t_j3] = matrix[t_i2][t_j2];
                matrix[t_i2][t_j2] = tmp;
                t_j1++;
                t_i2++;
                t_j3--;
                t_i4--;
            }

            i1++;
            j1++;
            i2++;
            j2--;
            i3--;
            j3--;
            i4--;
            j4++;

        }
    }
}
