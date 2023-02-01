package org.top.数字处理;
//https://leetcode.cn/problems/move-zeroes
//移动零
/*
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
请注意 ，必须在不复制数组的情况下原地对数组进行操作。

输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]

输入: nums = [0]
输出: [0]

提示:

    1 <= nums.length <= 10^4
    -2^31 <= nums[i] <= 2^31 - 1

进阶：你能尽量减少完成的操作次数吗？
 */
public class move_zeroes_1 {
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
