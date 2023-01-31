package org.top.动态规划;
//https://leetcode.cn/problems/longest-increasing-subsequence/
//最长递增子序列
/*
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

输入：nums = [0,1,0,3,2,3]
输出：4

输入：nums = [7,7,7,7,7,7,7]
输出：1

提示：
    1 <= nums.length <= 2500
    -10^4 <= nums[i] <= 10^4
进阶：
    你能将算法的时间复杂度降低到 O(n log(n)) 吗?

 */
public class longest_increasing_subsequence_4 {
    public static void main(String[] args) {

    }

    // 普通dp,O(n^2)
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] f = new int[nums.length];
        f[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            res = Math.max(res, f[i]);
        }

        return res;
    }

    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // f[2] = 3，代表长度为2的递增序列以元素3为结尾
        // 用这种方式存储能保证，index越大，f[index]就越大，保证了递增性，所以可以对f用二分
        int[] f = new int[nums.length + 1];
        int index = 1;
        f[index] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > f[index]) {
                f[++index] = nums[i];
            } else {
                int l = 1, r = index, pos = 0;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (f[mid] < nums[i]) {
                        l = mid + 1;
                        pos = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                // 二分查询index尽量大，并且符合递增序列条件的下标
                f[pos + 1] = nums[i];
            }
        }

        return index;

    }
}
