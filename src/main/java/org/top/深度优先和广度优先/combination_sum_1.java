package org.top.深度优先和广度优先;

import java.util.*;

//组合总和
//https://leetcode-cn.com/problems/combination-sum
/*
给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
并以列表形式返回。你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。

输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。

输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]

输入: candidates = [2], target = 1
输出: []

提示：

    1 <= candidates.length <= 30
    2 <= candidates[i] <= 40
    candidates 的所有元素 互不相同
    1 <= target <= 40

 */
public class combination_sum_1 {
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
