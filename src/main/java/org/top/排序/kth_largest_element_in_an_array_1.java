package org.top.排序;

import static org.practice.template.soft.QuickSort.swap;
//数组中的第K个最大元素
//https://leetcode-cn.com/problems/kth-largest-element-in-an-array
/*
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

输入: [3,2,1,5,6,4], k = 2
输出: 5

输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4

提示：

    1 <= k <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4

 */
public class kth_largest_element_in_an_array_1 {
    public static void main(String[] args) {
        int[] param = new int[]{1,3,3,4,5,6};
        // partition返回的是下标，如果要求的是第k个最小的元素，则 q == k - 1
        System.out.println(findKthLargest2(param, 2));

    }

    public static int findKthLargest2(int[] nums, int k) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int pos = partition2(nums, l, r);
            if (nums.length - k == pos) {
                return nums[pos];
            } else if (pos < nums.length - k) {
                l = pos + 1;
            } else {
                r = pos - 1;
            }
        }

        return nums[0];
    }

    public static int partition2(int[] nums, int left, int right) {
        int left_tmp = left - 1;
        int right_tmp = right;
        int index = left;

        while (index < right_tmp) {
            if (nums[index] < nums[right]) {
                swap(nums, index++, ++left_tmp);
            } else if (nums[index] > nums[right]) {
                swap(nums, index, --right_tmp);
            } else {
                index++;
            }
        }
        swap(nums, right, right_tmp);
        return left_tmp + 1;
    }

    // 倒序排
    public int findKthLargest(int[] nums, int k) {
        if (k < 1 || k > nums.length) {
            return -1;
        }
        int index = qs(nums, 0, nums.length - 1, k);
        if (index < 0 || index >= nums.length) {
            return -1;
        }
        return nums[index];

    }

    public int qs(int[] nums, int left, int right, int k) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return left;
        }
        int p = left + (int) ((right - left) * Math.random());
        swap(nums, p, right);

        while (left <= right) {
            int pos = partition3(nums, left, right);
            if (pos < k-1) {
                left = pos + 1;
            } else if (pos > k-1) {
                right = pos - 1;
            } else {
                return pos;
            }
        }
        return -1;
    }

    private int partition3(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return left;
        }
        int left_tmp = left - 1, index = left;
        while (index < right) {
            if (nums[index] > nums[right]) {
                swap(nums, ++left_tmp, index);
            }
            index++;
        }
        swap(nums, left_tmp + 1, right);
        return left_tmp + 1;
    }

    // OJ提交的时候如果用递归容易造成栈溢出
    public static int func(int[] arr, int k) {
        int res = quickSelect(arr, 0, arr.length - 1, k);
        return res;
    }

    public static int quickSelect(int[] arr, int l, int r, int k) {
        int q = partition(arr, l, r);
        if (q == k) {
            return arr[q];
        } else {
            return q < k ? quickSelect(arr, q + 1, r, k) : quickSelect(arr, l, q - 1, k);
        }
    }

    private static int partition(int[] arr, int l, int r) {
        int x = arr[r];
        int left_tmp = l - 1;
        int right_tmp = r;
        int index = l;
        while (index < right_tmp) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++left_tmp);
            } else {
                swap(arr, index, --right_tmp);
            }
        }
        swap(arr, r, right_tmp);
        return left_tmp + 1;
    }

    public static int partition1(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        // 只排小的，大的不动
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }



}
