package org.top;

import java.util.*;

public class sudoku_solver {

    List<int[]> list = new ArrayList<>();
    boolean[][] row = new boolean[9][9];
    boolean[][] col = new boolean[9][9];
    boolean[][][] block = new boolean[3][3][9];
    boolean valid;

    public void solveSudoku(char[][] board) {

        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    list.add(new int[]{i, j});
                } else {
                    int num = board[i][j] - '0' - 1;
                    row[i][num] = col[j][num] = block[i / 3][j / 3][num] = true;
                }
            }
        }
        dfs(board, 0);
    }

    public void dfs(char[][] board, int index) {
        if (index == list.size()) {
            valid = true;
            return;
        }

        int[] arr = list.get(index);
        int i = arr[0], j = arr[1];

        for (int num = 1; num <= 9; num++) {
            if (!valid && !row[i][num-1] && !col[j][num-1] && !block[i/3][j/3][num-1]) {
                board[i][j] = (char) (num + '0');
                row[i][num-1] = col[j][num-1] = block[i/3][j/3][num-1] = true;
                dfs(board, index + 1);
                row[i][num-1] = col[j][num-1] = block[i/3][j/3][num-1] = false;
            }
        }
    }


    int[] row1 = new int[9];
    int[] col1 = new int[9];
    int[][] bloack1 = new int[9][9];

    boolean valid1;
    List<int[]> list1 = new ArrayList<>();

    // 000001010 -> 可以填的数字为2和4
    public void solveSudoku1(char[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    list1.add(new int[]{i,j});
                } else {
                    int num = board[i][j] - '0' - 1;
                    // 如果数字为3，左移3-1位
                    fill(num, i, j);
                }
            }
        }
        dfs1(board, 0);
    }

    private void dfs1(char[][] board, int index) {
        if (index == list1.size()) {
            valid1 = true;
            return;
        }

        int[] arr = list1.get(index);
        int i = arr[0], j = arr[1];
        int mask = ~(row1[i] | col1[j] | bloack1[i/3][j/3]) & 0x1ff;
        for (; mask != 0 && !valid1; mask &= (mask-1)) {
            // 000010100 -> 000000100
            int d = mask & (-mask);
            // 左移num位，那么原来填的数字为num+1，左移多少位可以用d-1后的二进制有多少个1得出
            // 9位二进制，1<<0等于1，最多1<<8
            int num = Integer.bitCount(d - 1);
            fill(num, i, j);
            board[i][j] = (char) (num + 1 + '0');
            dfs1(board, index + 1);
            fill(num, i, j);
        }
    }

    public void fill(int num, int i, int j) {
        row1[i] ^= (1<<num);
        col1[j] ^= (1<<num);
        bloack1[i/3][j/3] ^= (1<<num);
    }

}
