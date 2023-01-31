package org.top.二分查找;
//https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
//在排序数组中查找元素的第一个和最后一个位置
/*
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]

输入：nums = [], target = 0
输出：[-1,-1]

提示：

    0 <= nums.length <= 10^5
    -10^9 <= nums[i] <= 10^9
    nums 是一个非递减数组
    -10^9 <= target <= 10^9

 */
public class find_first_and_last_position_of_element_in_sorted_array_1 {
    public static void main(String[] args) {
        int[] arr = new int[]{5,7,7,8,8,10};
        int[] res = searchRange(arr, 8);
        System.out.println(res.length);
    }
    // 二分查询的范围查询
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1,-1};
        }
        int lower = binarySearch(nums, target, true);
        int height = binarySearch(nums, target, false);

        return new int[]{lower, height};
    }

    public static int binarySearch(int[] nums, int target, boolean isLower) {
        int left = 0, right = nums.length - 1;
        int ans = -1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (isLower && nums[mid] == target) {
                ans = mid;
                right = mid - 1;
            } else if (!isLower && nums[mid] == target) {
                ans = mid;
                left = mid + 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static int[] searchRange1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int l = -1, r = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
                r = mid;
            }
        }

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                right = mid - 1;
                l = mid;
            }
        }

        return new int[]{l, r};
    }
}
