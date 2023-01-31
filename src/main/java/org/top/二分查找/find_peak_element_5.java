package org.top.二分查找;
//https://leetcode-cn.com/problems/find-peak-element
//寻找峰值
/*
峰值元素是指其值严格大于左右相邻值的元素。
给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
你可以假设 nums[-1] = nums[n] = -∞ 。
你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。

输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5
解释：你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。

提示：

    1 <= nums.length <= 1000
    -2^31 <= nums[i] <= 2^31 - 1
    对于所有有效的 i 都有 nums[i] != nums[i + 1]

 */
public class find_peak_element_5 {
    public static void main(String[] args) {
        int[] nums = new int[]{1};
        int peakElement = findPeakElement(nums);
        System.out.println(peakElement);
    }

    // 1,2,3 峰值是3
    // 3,2,2 峰值是3
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                ans = mid;
                break;
            } else if (compare(nums, mid - 1, mid) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static int compare(int[] nums, int index1, int index2) {
        int[] arr1 = get(nums, index1);
        int[] arr2 = get(nums, index2);

        if (arr1[0] != arr2[0]) {
            return arr1[0] - arr2[0];
        }

        return arr1[1] - arr2[1];

    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况

    public static int[] get(int[] nums, int index) {
        if (index < 0 || index >= nums.length) {
            return new int[]{0,0};
        }
        return new int[]{1, nums[index]};
    }

}
