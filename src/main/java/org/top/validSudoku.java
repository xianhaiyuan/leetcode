package org.top;
//https://leetcode.cn/problems/valid-sudoku/
//有效的数独
public class validSudoku {
    public static void main(String[] args) {
        char[][] arr = new char[][]{{'.','8','7','6','5','4','3','2','1'},{'2','.','.','.','.','.','.','.','.'},{'3','.','.','.','.','.','.','.','.'},{'4','.','.','.','.','.','.','.','.'},{'5','.','.','.','.','.','.','.','.'},{'6','.','.','.','.','.','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'8','.','.','.','.','.','.','.','.'},{'9','.','.','.','.','.','.','.','.'}};
        boolean validSudoku = isValidSudoku(arr);
        System.out.println(validSudoku);
    }

    static boolean valid = true;
    static int[] row = new int[9];
    static int[] col = new int[9];
    static int[][] block = new int[9][9];

    public static boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0' - 1;
                if (!isValid(num, i, j)) {
                    return false;
                }
                fill(num, i, j);
            }
        }

        return valid;
    }

    public static boolean isValid(int num, int i, int j) {
        int mask = (1<<num) & (row[i] | col[j] | block[i/3][j/3]);
        if (mask != 0) {
            return false;
        }
        return true;
    }

    public static void fill(int num, int i, int j) {
        row[i] ^= (1<<num);
        col[j] ^= (1<<num);
        block[i/3][j/3] ^= (1<<num);
    }
}
