package org.top.深度优先和广度优先;

import java.util.*;

//https://leetcode.cn/problems/eight-queens-lcci/
//八皇后
/*
设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。
这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。

 输入：4
 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 解释: 4 皇后问题存在如下两个不同的解法。
[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */
public class eight_queens_lcci_3 {

    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens1(4);
        System.out.println(lists.size());
    }

    static int[] row;

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        row = new int[n];
        Arrays.fill(row, -1);
        dfs(0, n, ans, new ArrayList<>());
        return ans;
    }

    public static void dfs(int index, int n, List<List<String>> ans, List<String> path) {
        if (index == n) {
            ans.add(new ArrayList<>(path));
        }

        for (int i = 0; i < n; i++) {
            if (valid(index, i)) {
                char[] arr = new char[n];
                Arrays.fill(arr, '.');
                arr[i] = 'Q';
                path.add(new String(arr));
                row[index] = i;
                dfs(index + 1, n, ans, path);
                path.remove(index);
                arr[index] = '.';
                row[index] = -1;
            }
        }

    }

    private static boolean valid(int index, int pos) {
        for (int i = 0; i < index; i++) {
            if (row[i] == pos || Math.abs(i - index) == Math.abs(row[i] - pos)) {
                return false;
            }
        }
        return true;
    }


    public static List<List<String>> solveNQueens1(int n) {
        List<List<String>> ans = new ArrayList<>();
        dfs1(0, n, ans, new ArrayList<>(), 0, 0, 0);
        return ans;
    }


    public static void dfs1(int index, int n, List<List<String>> ans, List<String> path, int col, int pie, int na) {
        if (index == n) {
            ans.add(new ArrayList<>(path));
        }

        int mask = ~(col | pie | na) & ((1<<n) - 1);
        while (mask != 0) {
            int p = mask&(-mask);
            int pos = Integer.bitCount(p - 1);
            char[] arr = new char[n];
            Arrays.fill(arr, '.');
            arr[pos] = 'Q';
            path.add(new String(arr));
            dfs1(index + 1, n, ans, path, col|p, (pie|p)>>1, (na|p)<<1);
            path.remove(index);

            // mask = mask & (~p)
            mask &= (mask-1);
        }

    }

}
