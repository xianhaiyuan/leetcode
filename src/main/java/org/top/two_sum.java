package org.top;

import java.util.*;

//两数之和
//https://leetcode-cn.com/problems/two-sum
public class two_sum {

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,4};
        int[] ans = twoSum1(arr, 6);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(target - nums[i]);
            if (j != null) {
                return new int[]{i, j};
            }
            map.put(nums[i], i);

        }

        return null;
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        if (nums == null || nums.length < 2) {
            return ans;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return ans;
    }
}
