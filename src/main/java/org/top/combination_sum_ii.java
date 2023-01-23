package org.top;

import java.util.*;

//https://leetcode.cn/problems/combination-sum-ii
//组合总和 II
public class combination_sum_ii {
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
