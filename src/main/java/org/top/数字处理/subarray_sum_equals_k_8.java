package org.top.数字处理;

import java.util.*;

//https://leetcode-cn.com/problems/subarray-sum-equals-k
//和为K的子数组
/*
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。

输入：nums = [1,1,1], k = 2
输出：2

输入：nums = [1,2,3], k = 3
输出：2

提示：

    1 <= nums.length <= 2 * 10^4
    -1000 <= nums[i] <= 1000
    -10^7 <= k <= 10^7

 */
public class subarray_sum_equals_k_8 {
    public static void main(String[] args) {

    }

    /*
      j...i, j < i, 计算有多少个 sum[j,...,i] == k, 转化为计算前缀和 pre[i] - pre[j-1] == k,
      那么可以遍历所有数组的同时计算前缀和pre, 并将这个前缀和出现的次数保存到map中，如果 pre[i] - k 存在于map，则 count += map.get(pre[i] - k),
      初始化 map.put(0, 1)
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        int res = 0;
        int pre = 0;
        // 保存前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)) {
                res += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }

        return res;
    }

}
