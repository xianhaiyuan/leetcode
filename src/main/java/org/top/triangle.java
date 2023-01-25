package org.top;

import java.util.*;

//https://leetcode.cn/problems/triangle
//三角形最小路径和
public class triangle {
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
