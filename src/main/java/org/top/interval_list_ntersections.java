package org.top;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.cn/problems/interval-list-intersections/
//区间列表的交集
public class interval_list_ntersections {
    public static void main(String[] args) {

    }
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int m = firstList.length, n = secondList.length;
        int i = 0, j = 0;
        List<int[]> ans = new ArrayList<>();
        while (i < m && j < n) {
            int min = Math.min(firstList[i][1], secondList[j][1]);
            int max = Math.max(firstList[i][0], secondList[j][0]);
            if (min >= max) {
                ans.add(new int[]{max, min});
            }
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return ans.toArray(new int[0][]);
    }
}
