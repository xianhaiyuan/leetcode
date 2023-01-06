package org.top;

import java.util.*;

//组合总和
//https://leetcode-cn.com/problems/combination-sum
public class combination_sum {
    public static void main(String[] args) {

        int[] arr = new int[] {3,2,6,7};
        int target = 7;
        List<List<Integer>> lists = combinationSum(arr, target);
        System.out.println(lists.size());

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        // 排序是剪枝的前提
        Arrays.sort(candidates);
        dfs(candidates, target, ans, new LinkedList<>(), 0);
        return ans;
    }

    public static void dfs(int[] candidates, int target, List<List<Integer>> ans, Deque<Integer> path, int index) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 如果每次从0开始迭代，会有重复的答案添加
        for (int i = index; i < candidates.length; i++) {
            // 剪枝
            if (target - candidates[i] < 0) {
                break;
            }
            path.addLast(candidates[i]);
            // 可以重复添加当前元素，所以递归的下标从当前下标开始
            dfs(candidates, target - candidates[i], ans, path, i);
            path.removeLast();
        }
    }
}
