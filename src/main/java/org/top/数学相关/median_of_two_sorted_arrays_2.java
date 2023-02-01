package org.top.数学相关;
//https://leetcode-cn.com/problems/median-of-two-sorted-arrays
//寻找两个正序数组的中位数
/*
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。

输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

提示：

    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -10^6 <= nums1[i], nums2[i] <= 10^6

 */
public class median_of_two_sorted_arrays_2 {
    public static void main(String[] args) {
        int[] num1 = new int[]{0,0,0,0,0};
        int[] num2 = new int[]{-1,0,0,0,0,0,1};
        System.out.println(findMedianSortedArrays(num1, num2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 数组长度小的在前，不然下标j会出现溢出
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0, right = nums1.length, n1 = nums1.length, n2 = nums2.length;
        int median1 = 0, median2 = 0;
        // 有序二分
        // 当right==nums1.length时，右侧元素个数为0
        while (left <= right) {
            int i = left + (right - left) / 2;
            // i + j = (n1 - i) + (n2 - j) + 1
            // i 为 在i前面的元素的个数，n1 - i为包括i在内的后面的元素个数
            // 如果n1+n2为奇数，那么我们使左边元素比右边元素多一个，ans就直接取max(左边元素)
            // 如果n1+n2为偶数，那么ans为(max(左边)+min(右边))/2
            // 如果公式用i + j = (n1 - i) + (n2 - j)，那么如果n1+n2为奇数时，ans为min(右边元素)
            int j = (n1 + n2 + 1) / 2 - i;

            int num_i_left = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int num_i_right = (i == n1 ? Integer.MAX_VALUE : nums1[i]);
            int num_j_left = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int num_j_right = (j == n2 ? Integer.MAX_VALUE : nums2[j]);

            // left_i 本来就比right_i小，所以只需要和right_j比较
            if (num_i_left <= num_j_right) {
                median1 = Math.max(num_i_left, num_j_left);
                median2 = Math.min(num_i_right, num_j_right);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (n1 + n2) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }
}
