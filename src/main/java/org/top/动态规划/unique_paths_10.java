package org.top.动态规划;
//https://leetcode-cn.com/problems/unique-paths
//不同路径
/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
问总共有多少条不同的路径？

输入：m = 3, n = 7
输出：28

输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下

输入：m = 7, n = 3
输出：28

输入：m = 3, n = 3
输出：6

提示：

    1 <= m, n <= 100
    题目数据保证答案小于等于 2 * 10^9

 */
public class unique_paths_10 {
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

    // 优化，只用一维数组，当前 = 左边 + 上边，上一行的值如果直接保存在数组中，那么当前 = 左边 + 当前
    public static int uniquePaths1(int m, int n) {
        int[] f = new int[n];
        for (int i = 0; i < f.length; i++) {
            f[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[j] = f[j-1] + f[j];
            }
        }
        return f[n - 1];
    }

}
