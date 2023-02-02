package org.top;
import org.top.common.ListNode;
import org.top.common.TreeNode;

import java.util.*;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'a'}};
        String word = "a";
        System.out.println(exist(board, word));
    }

    static boolean[][] vis;
    public static boolean exist(char[][] board, String word) {
           int m = board.length, n = board[0].length;
           vis = new boolean[m][n];
           boolean res = false;
           for (int i = 0; i < m; i++) {
               for (int j = 0; j < n; j++) {
                   if (!vis[i][j]) {
                       res = dfs(board, word, i, j, 0);
                       if (res) {
                           return true;
                       }
                   }
               }
           }

           return res;
    }

    private static boolean dfs(char[][] board, String word, int i, int j, int index) {

        if (board[i][j] != word.charAt(index)) {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }

        vis[i][j] = true;

        int[][] dir = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};

        for (int[] d : dir) {
            int i1 = i + d[0], j1 = j + d[1];
            if (i1 >= 0 && i1 < board.length && j1 >= 0 && j1 < board[0].length && !vis[i1][j1]) {
                vis[i1][j1] = true;
                boolean res = dfs(board, word, i1, j1, index + 1);
                if (res) {
                    return true;
                }
                vis[i1][j1] = false;
            }
        }

        return false;
    }


}
