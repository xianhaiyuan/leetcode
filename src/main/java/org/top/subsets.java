package org.top;

import java.util.*;

//https://leetcode.cn/problems/subsets
//子集
public class subsets {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        List<List<Integer>> ans = subsets1(arr);
        System.out.println(ans.size());

    }

    static List<Integer> t = new ArrayList<>();
    static List<List<Integer>> ans = new ArrayList<>();

    public static List<List<Integer>> subsets(int[] nums) {

        int n = nums.length;
        // {1,2,3} 模拟 000,001,010,011,100,110,111
        for (int mask = 0; mask < (1<<n); mask++) {
            t.clear();
            // 通过左移选取mask上面位为1的位置为下标取nums[i]
            for (int i = 0; i < n; i++) {
                System.out.println(Integer.toBinaryString(mask));
                System.out.println(Integer.toBinaryString((1<<i)));
                if ((mask & (1<<i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }

    public static List<List<Integer>> subsets1(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public static void dfs(int i, int[] nums) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }

        t.add(nums[i]);
        // 考虑选择当前位置
        dfs(i + 1, nums);
        t.remove(t.size() - 1);
        // 考虑不选择当前位置
        dfs(i + 1, nums);

    }
}
