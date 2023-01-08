package org.top;

import java.util.*;

//https://leetcode-cn.com/problems/subarray-sum-equals-k
//和为K的子数组
public class subarray_sum_equals_k {
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
