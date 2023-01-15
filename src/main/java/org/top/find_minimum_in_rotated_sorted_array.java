package org.top;
//https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
//寻找旋转排序数组中的最小值
public class find_minimum_in_rotated_sorted_array {
    public static void main(String[] args) {
        int[] arr = new int[] {1,3,3,3};
        int min = findMin1(arr);
        System.out.println(min);
    }

    public static int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                // 翻转后 4,5,6,7,0,1,2, 如果用一般的right = mid - 1
                // 当 mid = 4, 判断后 right = 3，就会漏掉正确答案
                right = mid;
            } else {
                // 用上面的例子，如果mid为3，left = 4，也不会错过答案
                left = mid + 1;
            }
        }
        return nums[right];
    }
    public static int findMin1(int[] nums) {

        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (compare(nums, mid - 1, mid) >= 0 && compare(nums, mid, mid + 1) <= 0) {
                return nums[mid];
            } else if (nums[0] > nums[nums.length - 1]) { // 翻转过

                if (compare(nums, mid, nums.length - 1) > 0) { // mid 处于翻转后的左边
                    l = mid + 1;
                } else if (compare(nums, mid - 1, mid) <= 0 && compare(nums, mid, mid + 1) <= 0){
                    // mid 处于翻转后的右边，只需一直往左找
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
