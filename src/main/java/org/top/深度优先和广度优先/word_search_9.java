package org.top.深度优先和广度优先;
//https://leetcode-cn.com/problems/word-search
//单词搜索
/*
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false

提示：

    m == board.length
    n = board[i].length
    1 <= m, n <= 6
    1 <= word.length <= 15
    board 和 word 仅由大小写英文字母组成

进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */
public class word_search_9 {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "SEE";
        System.out.println(exist1(board, word));
    }
    public static boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(char[][] board, boolean[][] visited, int i, int j, String word, int index) {

        if (board[i][j] != word.charAt(index)) {
            return false;
        } else if (index == word.length() - 1) {
            // 在 word.length() - 1 返回，那么就必须验证最后一个字符串是否相同
            return true;
        }

        // 只有valid认证后的才置visited
        visited[i][j] = true;

        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        boolean res = false;

        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[newi].length) {
                if (!visited[newi][newj]) {
                    // 易错点，不要在这里visited[newi][newj] = true，因为 board[newi][newj]还没有验证过
                    boolean flag = check(board, visited, newi, newj, word, index + 1);
                    if (flag) {
                        res = true;
                        break;
                    }
                }
            }
        }

        visited[i][j] = false;
        return res;
    }


    public static boolean exist1(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        return dfs1(board, word, 0, 0, 0, visited);
    }


    public static boolean dfs1(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }

        // 错误的解题，每次递归只能从四个方向入手，而不是遍历入手
        for (int g = i; g < board.length; g++) {
            for (int h = j; h < board.length; h++) {
                if (g>=0 && g<board.length && h>=0 && h < board[g].length &&
                        !visited[g][h] && board[g][h] == word.charAt(index)) {
                    visited[g][h] = true;
                    boolean res = dfs1(board, word, g+1, h, index + 1, visited);
                    if (res) {
                        return true;
                    }
                    res = res || dfs1(board, word, g-1, h, index+1, visited);
                    if (res) {
                        return true;
                    }
                    res = res || dfs1(board, word, g, h+1, index+1, visited);
                    if (res) {
                        return true;
                    }
                    res = res || dfs1(board, word, g, h-1, index+1, visited);
                    if (res) {
                        return true;
                    }

                    visited[g][h] = false;

                }
            }
        }

        return false;
    }
}
