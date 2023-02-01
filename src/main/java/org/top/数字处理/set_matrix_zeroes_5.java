package org.top.数字处理;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//https://leetcode.cn/problems/set-matrix-zeroes/
//矩阵置零
/*
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
输出：[[1,0,1],[0,0,0],[1,0,1]]

输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]

提示：

    m == matrix.length
    n == matrix[0].length
    1 <= m, n <= 200
    -2^31 <= matrix[i][j] <= 2^31 - 1

进阶：
    一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
    一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
    你能想出一个仅使用常量空间的解决方案吗？

 */
public class set_matrix_zeroes_5 {

    public static void main(String[] args) {
        int[][] arr = {{1,0,3}};
        setZeroes1(arr);
        System.out.println(arr.length);
    }

    // 使用一个变量，倒序遍历设置
    public static void setZeroes2(int[][] matrix) {
        // 决定最左侧一列是否要置0
        boolean colFlag = false;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
            }
            for (int j = 1; j < matrix[i].length; j++) {
                // 在[i][0]和[0][j]上做标记，用于后面再次遍历把第i行和第j列置0
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        // 第一行用作了标记，所以倒序遍历
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j > 0; j--) {
                // 第i行和第j列置0
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // 最左侧一列置0
            if (colFlag) {
                matrix[i][0] = 0;
            }
        }

    }

    // 使用两个变量
    public static void setZeroes1(int[][] matrix) {
        boolean rowFlag = false, colFlag = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
                break;
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowFlag = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (colFlag) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        if (rowFlag) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }

    }

    // set保存标记
     static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            Point p = (Point) obj;
            if (this.x == p.x && this.y == p.y) {
                return true;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Set<Point> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Point point = new Point(i, j);
                if (matrix[i][j] == 0 && !set.contains(point)) {
                    int i_ori = i, j_ori = j;
                    int i_cur = i, j_cur = j;

                    i_cur = add(i_ori, i_cur, m);

                    while (i_cur != -1) {
                        if (matrix[i_cur][j_ori] != 0) {
                            set.add(new Point(i_cur, j_ori));
                        }
                        matrix[i_cur][j_ori] = 0;
                        i_cur = add(i_ori, i_cur, m);
                    }

                    j_cur = add(j_ori, j_cur, n);

                    while (j_cur != -1) {
                        if (matrix[i_ori][j_cur] != 0) {
                            set.add(new Point(i_ori, j_cur));
                        }
                        matrix[i_ori][j_cur] = 0;
                        j_cur = add(j_ori, j_cur, n);
                    }

                }
            }
        }
    }

    public static int add(int origin, int cur, int n) {
        int i = cur + 1;
        if (i >= n) {
            i = 0;
        }
        if (i == origin) {
            return -1;
        }
        return i;
    }
}
