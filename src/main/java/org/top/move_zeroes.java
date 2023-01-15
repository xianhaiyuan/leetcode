package org.top;
//https://leetcode.cn/problems/move-zeroes
//移动零
public class move_zeroes {
    public static void main(String[] args) {
        int[] arr = new int[]{1,0,1};
        moveZeroes(arr);
        System.out.println(arr.length);
    }
    public static void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
