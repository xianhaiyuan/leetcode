package org.top;
//https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/
//寻找旋转排序数组中的最小值 II
public class find_minimum_in_rotated_sorted_array_ii {
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
