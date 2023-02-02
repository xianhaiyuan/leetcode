package org.top.动态规划;

import java.util.*;

//https://leetcode.cn/problems/triangle
//三角形最小路径和
/*
给定一个三角形 triangle ，找出自顶向下的最小路径和。
每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
输出：11
解释：如下面简图所示：
   2
  3 4
 6 5 7
4 1 8 3
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

输入：triangle = [[-10]]
输出：-10

提示：

    1 <= triangle.length <= 200
    triangle[0].length == 1
    triangle[i].length == triangle[i - 1].length + 1
    -10^4 <= triangle[i][j] <= 10^4

 */
public class triangle_9 {
    public static void main(String[] args) {
        int[][] arr = {{-1},{2,3},{1,-1,-3}};
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list1 = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                list1.add(arr[i][j]);
            }
            list.add(list1);
        }

        int res = minimumTotal(list);
        System.out.println(res);

    }
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(n-1).size();
        int[][] f = new int[n][m];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (j > 0) {
                    f[i][j] = Math.min(f[i-1][j-1], f[i-1][j]) + list.get(j);
                } else {
                    f[i][j] = f[i-1][j] + list.get(j);
                }
                // 左边和右边那条边
                f[i][i] = f[i-1][i-1] + list.get(i);
            }
        }

        int min = f[n-1][0];
        for (int i = 1; i < m; i++) {
            min = Math.min(min, f[n-1][i]);
        }
        return min;
    }

    public static int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];

        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            // 右边那条边
            f[i] = f[i-1] + triangle.get(i).get(i);
            // 依赖前面一个元素用倒序
            for (int j = i - 1; j > 0; j--) {
                f[j] = Math.min(f[j], f[j-1]) + triangle.get(i).get(j);
            }
            // 左边那条边
            f[0] += triangle.get(i).get(0);
        }

        int min = f[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, f[i]);
        }
        return min;
    }
}
