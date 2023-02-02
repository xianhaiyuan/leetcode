package org.top.数字处理;
//https://leetcode.cn/problems/spiral-matrix-ii/
//螺旋矩阵 II
/*
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。

输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]

输入：n = 1
输出：[[1]]
 */
public class spiral_matrix_ii_7 {
    public static void main(String[] args) {
        int[][] arr = generateMatrix(3);
        System.out.println(arr);
    }
    public static int[][] generateMatrix(int n) {
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, target = n * n;
        while (num <= target) {
            for (int i = left; i <= right; i++) mat[top][i] = num++;
            top++;
            for (int i = top; i <= bottom; i++) mat[i][right] = num++;
            right--;
            for (int i = right; i >= left; i--) mat[bottom][i] = num++;
            bottom--;
            for (int i = bottom; i >= top; i--) mat[i][left] = num++;
            left++;
        }
        return mat;
    }
}
