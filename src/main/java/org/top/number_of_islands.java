package org.top;
//https://leetcode-cn.com/problems/number-of-islands
//岛屿数量
public class number_of_islands {
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
        findLand(grid, m, n, visited, i - 1, j);
        findLand(grid, m, n, visited, i + 1, j);
        findLand(grid, m, n, visited, i, j - 1);
        findLand(grid, m, n, visited, i, j + 1);
    }
}
