package org.top.单调栈;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.cn/problems/sum-of-subarray-minimums
//子数组的最小值之和
/*
给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
由于答案可能很大，因此 返回答案模 10^9 + 7 。

输入：arr = [3,1,2,4]
输出：17
解释：
子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。

输入：arr = [11,81,94,43,3]
输出：444

提示：

    1 <= arr.length <= 3 * 10^4
    1 <= arr[i] <= 3 * 10^4

 */
public class sum_of_subarray_minimums_3 {
    public static void main(String[] args) {
        int[] arr = {3,1,2,4,1};

        int res = sumSubarrayMins(arr);
        System.out.println(res);
    }

    /*
        3,1,2,4,1
        元素2的left为1，right为4，2的贡献值为(2-1)*2*(4-2)=4，即子集为{2}和{2,4}，求出每个元素的贡献值相加得到答案
        贡献值为(left-i)*a[i]*(right-i)
        6,2,7,4,5,8,1
        元素4的计算贡献值的子集为{7,4},{4},{7,4,5},{7,4,5,8},{4,5},{4,5,8}，贡献值24
        left = 1, right = 6, i = 3, (3-1)*4*(6-3)=24
     */
    public static int sumSubarrayMins(int[] arr) {

        int n = arr.length;
        int mod = 1000000007;
        // 用long防溢出
        long ans = 0;

        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> stack = new LinkedList<>();

        // 第一次循环先找到所有元素的左边界
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = stack.peek();
            }

            stack.push(i);
        }

        stack.clear();

        // 第二次循环找到所有元素的右边界
        /*
            int[] arr = {1,2,2,1};
            left:-1,0,1,0
            right:4,3,3,4 (如果计算right时没有>=，就漏了{2,2})
            在计算左边界或者右边界时将一侧设置为求解小于等于E的元素，目的是为了解决当一个子数组中有两个最小值元素时，
            不重复且不遗漏地统计每一个子数组。
         */
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                right[i] = n;
            } else {
                right[i] = stack.peek();
            }

            stack.push(i);

        }

        for (int i = 0; i < n; i++) {
            // 按照贡献度计算即可
            // 注意此处left[i]和right[i]实际上记录的是左边界-1和右边界+1，和上面思路中有些区别，便于计算
            ans = (ans + (long)(i - left[i]) * arr[i] * (right[i] - i)) % mod;
        }

        return (int) ans;
    }

}
