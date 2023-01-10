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
        if (nums1.length < nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0, right = nums1.length, n1 = nums1.length, n2 = nums2.length;
        int median1 = 0, median2 = 0;
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


    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int left = 0, right = n1;
        int m1 = 0, m2 = 0;

        while (left <= right) {
            int i = (left + right) / 2;
            int j = (n1 + n2) / 2 - i;

            int left_i = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int right_i = i == n1 ? Integer.MAX_VALUE : nums1[i];
            int left_j = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int right_j = j == n2 ? Integer.MAX_VALUE : nums2[j];

            if (left_i < right_j) {
                m1 = Math.max(left_i, left_j);
                m2 = Math.min(right_i, right_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (n1 + n2) % 2 == 0 ? (m1 + m2) / 2.0 : m2;

    }
}
