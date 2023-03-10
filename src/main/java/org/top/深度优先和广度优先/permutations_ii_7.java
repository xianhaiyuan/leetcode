package org.top.深度优先和广度优先;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode-cn.com/problems/permutations-ii
//全排列 II
/*
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

提示：

    1 <= nums.length <= 8
    -10 <= nums[i] <= 10

 */
public class permutations_ii_7 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,1,2};
        List<List<Integer>> lists = permuteUnique(nums);
        System.out.println(lists.size());
    }

    static boolean[] visited;

    public static List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        visited = new boolean[nums.length];
        List<Integer> perm = new ArrayList<>();
        Arrays.sort(nums);
        func(nums, 0, perm, ans);
        return ans;

    }

    // index 指的是list元素的下标
    public static void func(int[] nums, int index, List<Integer> list, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排序过后保证相同的数字的在一起，如果nums[i] == nums[i-1] && !visited[i-1]，则选用nums[i]后的序列与之前用nums[i-1]的序列一样
            // 如果nums[i] == nums[i-1] 但visited[i-1] == true && visited[i] == false 那么vistited[i]是第一次选

            // i > 0 && nums[i] == nums[i - 1] && visited[i - 1] 这个条件也是可以的，但是剪的支会少一些
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            func(nums, index + 1, list, ans);
            visited[i] = false;
            list.remove(index);
        }
    }

}
