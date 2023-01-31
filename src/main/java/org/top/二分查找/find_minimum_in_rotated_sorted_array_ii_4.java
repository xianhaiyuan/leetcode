package org.top.二分查找;
//https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/
//寻找旋转排序数组中的最小值 II
/*
已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
    若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
    若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
你必须尽可能减少整个过程的操作步骤。

输入：nums = [1,3,5]
输出：1

输入：nums = [2,2,2,0,1]
输出：0
 */
public class find_minimum_in_rotated_sorted_array_ii_4 {
    public static void main(String[] args) {
        // 3,1,1
        // 2,2,2,0,1
        int[] arr = new int[] {3,3,3,3,1,3,3};
        System.out.println(findMin(arr));
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
            } else if (nums[mid] > nums[right]) {
                // 用上面的例子，如果mid为3，left = 4，也不会错过答案
                left = mid + 1;
            } else {
                // right一直缩减就一定能碰到和最小值比较的情况
                right--;
            }
        }
        return nums[left];
    }
}
