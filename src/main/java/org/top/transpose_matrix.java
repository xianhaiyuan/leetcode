package org.top;
//https://leetcode.cn/problems/transpose-matrix
//转置矩阵
public class transpose_matrix {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] transpose = transpose(arr);
        System.out.println(transpose.length);
    }
    public static int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] t = new int[n][m];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                t[j][i] = matrix[i][j];
            }
        }
        return t;
    }
}
