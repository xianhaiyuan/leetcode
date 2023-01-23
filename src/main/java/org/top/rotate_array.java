package org.top;
//https://leetcode.cn/problems/rotate-array
//轮转数组
public class rotate_array {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int k = 3;
        rotate1(arr, k);
        System.out.println(arr.length);
    }
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[(i+k)%n] = nums[i];
        }
        System.arraycopy(arr, 0, nums, 0, n);
    }

    // 数组翻转
    public static void rotate1(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

}
