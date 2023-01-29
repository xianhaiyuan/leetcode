package org.top;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.cn/problems/sum-of-subarray-minimums
//子数组的最小值之和
public class sum_of_subarray_minimums {
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
            int[] arr = {3,1,2,4,1};
            left:-1,-1,1,2,1
            right:1,5,4,4,5
            在计算左边界或者右边界时将一侧设置为求解小于等于E的元素，目的是为了解决当一个子数组中有两个最小值元素时（比如[3,1,2,4,1]中有两个1），
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
