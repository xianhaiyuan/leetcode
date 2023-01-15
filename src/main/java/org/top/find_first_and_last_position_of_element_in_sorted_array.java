package org.top;
//https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
//在排序数组中查找元素的第一个和最后一个位置
public class find_first_and_last_position_of_element_in_sorted_array {
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
}
