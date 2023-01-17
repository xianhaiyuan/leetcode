package org.top;

public class word_search {
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
            return true;
        }

        visited[i][j] = true;

        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        boolean res = false;

        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[newi].length) {
                if (!visited[newi][newj]) {
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
