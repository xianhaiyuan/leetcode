package org.top.二分查找;
//https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
//寻找旋转排序数组中的最小值
/*
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
    若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
    若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

输入：nums = [3,4,5,1,2]
输出：1
解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。

输入：nums = [4,5,6,7,0,1,2]
输出：0
解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。

输入：nums = [11,13,15,17]
输出：11
解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。

提示：

    n == nums.length
    1 <= n <= 5000
    -5000 <= nums[i] <= 5000
    nums 中的所有整数 互不相同
    nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转

 */
public class find_minimum_in_rotated_sorted_array_3 {
    public static void main(String[] args) {
        int[] arr = new int[] {4,5,6,7,0,1,2};
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
