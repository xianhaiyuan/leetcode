package org.top.单调栈;

import java.util.*;

//https://leetcode.cn/problems/largest-rectangle-in-histogram
//柱状图中最大的矩形
/*
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。

输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10

输入： heights = [2,4]
输出： 4
 */
public class largest_rectangle_in_histogram_2 {

    public static void main(String[] args) {
        int[] arr = {2,1,2};
        System.out.println(largestRectangleArea(arr));
    }

    // 单调栈
    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return heights[0];
        }

        int res = 0;

        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;
        len += 2;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>(len);
        // 先放入哨兵，在循环里就不用做栈的非空判断
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                // 计算宽度一定要用stack.peekLast()，虽然可能会有一些元素pop出去，但是这个递增的关系还是存在
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
    }

    public static int largestRectangleArea1(int[] heights) {

        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        // 用于如果一直递增
        Arrays.fill(right, n);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]) ;
        }
        return ans;
    }
}
