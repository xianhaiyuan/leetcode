package org.top;

import org.top.common.Node;
import org.top.common.TreeNode;

import java.util.*;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {
        int[] arr = {2,1,2};
        System.out.println(f(arr));
    }

    public static int f(int[] heights) {
        int n = heights.length;
        int[] h = new int[n+2];
        System.arraycopy(heights, 0, h, 1, n);
        heights = h;
        heights[0] = 0;
        heights[n+1] = 0;

        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        stack.push(0);
        n+=2;
        for (int i = 1; i < n; i++) {

            while (heights[i] < heights[stack.peek()]) {
                int right = stack.pop();
                ans = Math.max(ans, (i-right)*heights[right]);
            }


            stack.push(i);
        }

        return ans;
    }


}
