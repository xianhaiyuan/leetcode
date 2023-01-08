package org.top;
//https://leetcode-cn.com/problems/unique-paths
//不同路径
public class unique_paths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
    }
    public static int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                if (i == 0 && j == 0) {
                    f[i][j] = 1;
                    continue;
                }
                int top = i - 1 >= 0 ? f[i-1][j] : 0;
                int left = j - 1 >= 0 ? f[i][j-1] : 0;
                f[i][j] = top + left;
            }
        }
        return f[m-1][n-1];
    }

}
