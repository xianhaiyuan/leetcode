package org.top.深度优先和广度优先;

import java.util.*;
//https://leetcode-cn.com/problems/sudoku-solver
//解数独
/*
编写一个程序，通过填充空格来解决数独问题。

数独的解法需 遵循如下规则：

    数字 1-9 在每一行只能出现一次。
    数字 1-9 在每一列只能出现一次。
    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）

数独部分空格内已填入了数字，空白格用 '.' 表示。

输入：board =
[
["5","3",".",".","7",".",".",".","."],
["6",".",".","1","9","5",".",".","."],
[".","9","8",".",".",".",".","6","."],
["8",".",".",".","6",".",".",".","3"],
["4",".",".","8",".","3",".",".","1"],
["7",".",".",".","2",".",".",".","6"],
[".","6",".",".",".",".","2","8","."],
[".",".",".","4","1","9",".",".","5"],
[".",".",".",".","8",".",".","7","9"]
]
输出：
[
["5","3","4","6","7","8","9","1","2"],
["6","7","2","1","9","5","3","4","8"],
["1","9","8","3","4","2","5","6","7"],
["8","5","9","7","6","1","4","2","3"],
["4","2","6","8","5","3","7","9","1"],
["7","1","3","9","2","4","8","5","6"],
["9","6","1","5","3","7","2","8","4"],
["2","8","7","4","1","9","6","3","5"],
["3","4","5","2","8","6","1","7","9"]
]
解释：输入的数独如上图所示，唯一有效的解决方案如下所示：

提示：

    board.length == 9
    board[i].length == 9
    board[i][j] 是一位数字或者 '.'
    题目数据 保证 输入数独仅有一个解

 */
public class sudoku_solver_8 {

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
        // 把 (~x)&0x1ff 为0的位全部变成1，1的位变成0
        int mask = ~(row1[i] | col1[j] | bloack1[i/3][j/3]) & 0x1ff;
        //每执行一次x = x&(x-1)，会将x用二进制表示时最右边的一个1变为0
        for (; mask != 0 && !valid1; mask &= (mask-1)) {
            // 000010100 -> 000000100, 保留最右边的1
            int d = mask & (-mask);
            // 左移num位，那么原来填的数字为num+1，左移多少位可以用d-1后的二进制有多少个1得出
            // 9位二进制，1<<0等于1，最多1<<8
            // num直接是从0开始的下标
            int num = Integer.bitCount(d - 1);
            fill(num, i, j);
            board[i][j] = (char) (num + 1 + '0');
            dfs1(board, index + 1);
            // 因为使用亦或^，所以再调一次就能复原
            fill(num, i, j);
        }
    }

    public void fill(int num, int i, int j) {
        row1[i] ^= (1<<num);
        col1[j] ^= (1<<num);
        bloack1[i/3][j/3] ^= (1<<num);
    }

}
