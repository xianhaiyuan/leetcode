package org.top;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//https://leetcode.cn/problems/set-matrix-zeroes/
//矩阵置零
public class set_matrix_zeroes {

    public static void main(String[] args) {
        int[][] arr = {{1,0,3}};
        setZeroes1(arr);
        System.out.println(arr.length);
    }

    // 使用一个变量，倒序遍历设置
    public static void setZeroes2(int[][] matrix) {
        boolean colFlag = false;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
            }
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j > 0; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
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
