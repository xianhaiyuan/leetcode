package org.top.二分查找;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.cn/problems/find-in-mountain-array
//山脉数组中查找目标值
/*
给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
如果不存在这样的下标 index，就请返回 -1。
何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
首先，A.length >= 3
其次，在 0 < i < A.length - 1 条件下，存在 i 使得：

    A[0] < A[1] < ... A[i-1] < A[i]
    A[i] > A[i+1] > ... > A[A.length - 1]

你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：

    MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
    MountainArray.length() - 会返回该数组的长度

注意：

对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。

为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。

输入：array = [1,2,3,4,5,3,1], target = 3
输出：2
解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。

输入：array = [0,1,2,4,2,1], target = 3
输出：-1
解释：3 在数组中没有出现，返回 -1。

 */
public class find_in_mountain_array_2 {

    public static void main(String[] args) {
        MountainArray mountainArray = new MountainArray();
        // 0,1,2,4,2,1
        mountainArray.add(0);
        mountainArray.add(5);
        mountainArray.add(3);
        mountainArray.add(1);

        int res = findInMountainArray(1, mountainArray);
        System.out.println(res);

    }

    static class MountainArray {
        List<Integer> list = new ArrayList<>();
        public int get(int index) {
            return list.get(index);
        }
        public int length() {
            return list.size();
        }
        public void add(int i) {
            list.add(i);
        }
    }

    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int l = 0, r = mountainArr.length() - 1;
        // 这里不用l<=r,所以进入while至少两个元素,所以mid+1不会越界
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        int peek = l;
        int res = binarySearch(target, mountainArr, 0, peek, 1);
        if (res != -1) {
            return res;
        } else {
            res = binarySearch(target, mountainArr, peek + 1, mountainArr.length() - 1, -1);
        }

        return res;
    }

    public static int binarySearch(int target, MountainArray mountainArr, int left, int right, int flag) {

        // 上坡flag为1，下坡flag为-1

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) * flag < target * flag) {
                left = mid + 1;
            } else if (mountainArr.get(mid) * flag > target * flag) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
