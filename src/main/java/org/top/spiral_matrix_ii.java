package org.top;
//https://leetcode.cn/problems/spiral-matrix-ii/
//螺旋矩阵 II
public class spiral_matrix_ii {
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
