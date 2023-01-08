package org.top;
//https://leetcode-cn.com/problems/search-a-2d-matrix-ii
//搜索二维矩阵 II
public class search_a_2d_matrix_ii {
    public static void main(String[] args) {

    }
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }

        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {

            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }

        }

        return false;

    }
}
