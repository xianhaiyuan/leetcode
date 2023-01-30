package org.top.深度优先和广度优先;

import java.util.*;

//https://leetcode.cn/problems/combination-sum-ii
//组合总和 II
/*
给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的每个数字在每个组合中只能使用 一次 。
注意：解集不能包含重复的组合。

输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]

输入: candidates = [2,5,2,1,2], target = 5,
输出:
[
[1,2,2],
[5]
]

提示:

    1 <= candidates.length <= 100
    1 <= candidates[i] <= 50
    1 <= target <= 30

 */
public class combination_sum_ii_2 {
    public static void main(String[] args) {
        int[] arr = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> ans = combinationSum2(arr, target);
        System.out.println(ans.size());
    }
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        boolean[] visited = new boolean[candidates.length];
        dfs(candidates, target, 0, 0, new ArrayList<>(), ans, visited);
        return ans;
    }

    public static void dfs(int[] candidates, int target, int index, int cur, List<Integer> path, List<List<Integer>> ans,
                           boolean[] visited) {

        if (cur == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if ((i > 0 && candidates[i] == candidates[i-1] && !visited[i-1]) || cur + candidates[i] > target) {
                continue;
            }
            path.add(candidates[i]);
            visited[i] = true;
            dfs(candidates, target, i + 1, cur + candidates[i], path, ans, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }

    }
}
