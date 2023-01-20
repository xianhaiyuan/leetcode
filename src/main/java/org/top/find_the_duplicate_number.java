package org.top;
//https://leetcode-cn.com/problems/find-the-duplicate-number
//寻找重复数
public class find_the_duplicate_number {
    public static void main(String[] args) {
        int[] arr = {1,3,4,2,2};
        System.out.println(findDuplicate(arr));
    }

    // 由于数字都在 [1, n] 范围内
    // 每个位置 i 连一条 i→nums[i] 的边，存在重复的数字，所以必定存在环
    public static int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;

    }
}
