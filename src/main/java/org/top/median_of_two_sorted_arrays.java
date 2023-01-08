package org.top;
//https://leetcode-cn.com/problems/median-of-two-sorted-arrays
//寻找两个正序数组的中位数
public class median_of_two_sorted_arrays {
    public static void main(String[] args) {
        int[] num1 = new int[]{1,3};
        int[] num2 = new int[]{2};
        System.out.println(findMedianSortedArrays(num1, num2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0, right = nums1.length, m = nums1.length, n = nums2.length;
        int median1 = 0, median2 = 0;
        while (left <= right) {
            int i = left + (right - left) / 2;
            int j = (m + n + 1) / 2 - i;

            int num_i_left = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int num_i_right = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int num_j_left = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int num_j_right = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (num_i_left <= num_j_right) {
                median1 = Math.max(num_i_left, num_j_left);
                median2 = Math.min(num_i_right, num_j_right);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }
}
