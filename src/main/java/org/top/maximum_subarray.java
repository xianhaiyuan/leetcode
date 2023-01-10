package org.top;

//最大子序和
//https://leetcode.cn/problems/maximum-subarray
public class maximum_subarray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int i = maxSubArray(nums);
        System.out.println(i);
    }
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int pre = 0;
        int res = nums[0];

        // dp[i] = max(num[i], dp[i-1] + num[i])
        for (int num : nums) {
            pre = Math.max(num, pre + num);
            res = Math.max(pre, res);
        }
        return res;
    }

    public static int maxSubArray1(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
