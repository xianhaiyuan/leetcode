package org.top;
//https://leetcode-cn.com/problems/find-peak-element
//寻找峰值
public class find_peak_element {
    public static void main(String[] args) {
        int[] nums = new int[]{1};
        int peakElement = findPeakElement(nums);
        System.out.println(peakElement);
    }

    // 1,2,3 峰值是3
    // 3,2,2 峰值是3
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                ans = mid;
                break;
            } else if (compare(nums, mid - 1, mid) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static int compare(int[] nums, int index1, int index2) {
        int[] arr1 = get(nums, index1);
        int[] arr2 = get(nums, index2);

        if (arr1[0] != arr2[0]) {
            return arr1[0] - arr2[0];
        }

        return arr1[1] - arr2[1];

    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况

    public static int[] get(int[] nums, int index) {
        if (index < 0 || index >= nums.length) {
            return new int[]{0,0};
        }
        return new int[]{1, nums[index]};
    }

}
