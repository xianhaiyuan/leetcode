package org.top;
//https://leetcode.cn/problems/longest-increasing-subsequence/
//最长递增子序列
public class longest_increasing_subsequence {
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
