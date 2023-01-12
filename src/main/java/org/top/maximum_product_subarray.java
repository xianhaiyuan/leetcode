package org.top;
//https://leetcode-cn.com/problems/maximum-product-subarray
//乘积最大子数组
public class maximum_product_subarray {

    public static void main(String[] args) {
        int[] arr = new int[] {4,2,-1,3,5};
        System.out.println(maxProduct(arr));
    }

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
            int t = maxP;
            // maxP 代表第 i 个元素结尾的乘积最大子数组的乘积
            // 每次必须用上nums[i]，如4,2,-1，i等于2时，maxP = -1
            maxP = Math.max(Math.max(maxP * nums[i], nums[i]), minP * nums[i]);
            minP = Math.min(Math.min(t * nums[i], nums[i]), minP * nums[i]);
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
