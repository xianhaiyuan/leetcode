package org.top.深度优先和广度优先;
//https://leetcode-cn.com/problems/number-of-islands
//岛屿数量
/*
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
此外，你可以假设该网格的四条边均被水包围。

输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1

输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3

提示：

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    grid[i][j] 的值为 '0' 或 '1'


 */
public class number_of_islands_5 {
    public static void main(String[] args) {

    }

    static int res = 0;
    static boolean isFind = false;
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j]) {
                    isFind =false;
                    findLand(grid, grid.length, grid[i].length, visited, i, j);
                    if (isFind) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    public static void findLand(char[][] grid, int m, int n, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (visited[i][j] || grid[i][j] == '0') {
            return;
        }
        if (grid[i][j] == '1') {
            isFind = true;
        }

        visited[i][j] = true;
        int[][] dis = new int[][] {{0,-1,},{0,1},{-1,0},{1,0}};
        for (int[] d : dis) {
            findLand(grid, m, n, visited, i + d[0],j + d[1]);
        }
    }
}
