package org.top.贪心算法;
//https://leetcode-cn.com/problems/maximum-product-subarray
//乘积最大子数组
/*
给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
测试用例的答案是一个 32-位 整数。
子数组 是数组的连续子序列。

输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。

输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

提示:

    1 <= nums.length <= 2 * 10^4
    -10 <= nums[i] <= 10
    nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数

 */
public class maximum_product_subarray_2 {

    public static void main(String[] args) {
        int[] arr = new int[] {4,2,-1,3,5};
        System.out.println(maxProduct(arr));
    }

    // 贪心
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int res = nums[0];
        int maxP = nums[0];
        int minP = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int tmp_maxP = maxP;
            // maxP 代表第 i 个元素结尾的乘积最大子数组的乘积
            // 每次必须用上nums[i]，如4,2,-1，i等于2时，maxP = -1
            maxP = Math.max(Math.max(maxP * nums[i], nums[i]), minP * nums[i]);
            minP = Math.min(Math.min(tmp_maxP * nums[i], nums[i]), minP * nums[i]);
            res = Math.max(maxP, res);
        }
        return res;
    }

    public static int maxProduct1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] maxF = new int[n];
        int[] minF = new int[n];

        maxF[0] = nums[0];
        minF[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxF[i] = Math.max(Math.max(nums[i], maxF[i-1]*nums[i]), minF[i-1]*nums[i]);
            minF[i] = Math.min(Math.min(nums[i], maxF[i-1]*nums[i]), minF[i-1]*nums[i]);
        }
        int max = maxF[0];
        for (int i = 1; i < n; i++) {
            if (maxF[i] > max) {
                max = maxF[i];
            }
        }
        return max;
    }
}
