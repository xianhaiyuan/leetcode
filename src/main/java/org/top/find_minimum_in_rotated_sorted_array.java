package org.top;
//https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
//寻找旋转排序数组中的最小值
public class find_minimum_in_rotated_sorted_array {
    public static void main(String[] args) {
        int[] arr = new int[] {3,4,5,1,2};
        int min = findMin(arr);
        System.out.println(min);
    }
    public static int findMin(int[] nums) {

        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (compare(nums, mid - 1, mid) >= 0 && compare(nums, mid, mid + 1) <= 0) {
                return nums[mid];
            } else if (nums[0] > nums[nums.length - 1]) { // 翻转过

                if (compare(nums, mid, nums.length - 1) > 0) { // mid 处于翻转后的左边
                    l = mid + 1;
                } else if (compare(nums, mid - 1, mid) <= 0 && compare(nums, mid, mid + 1) <= 0){
                    // mid 处于翻转后的左边，只需一直往左找
                    r = mid - 1;
                }

            } else {
                // 没有翻转过
                return nums[0];
            }
        }
        return nums[l];
    }
    public static int compare(int[] nums, int i, int j) {
        if (i >= 0 && i < nums.length && j >= 0 && j < nums.length) {
            return nums[i] - nums[j];
        }
        if (i < 0 || j < 0 || i >= nums.length || j >= nums.length) {
            return 0;
        }
        return nums[i] - nums[j];
    }
}
