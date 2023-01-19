package org.top;

import java.util.Stack;

//https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
//二叉搜索树的后序遍历序列
public class er_cha_sou_suo_shu_de_hou_xu_bian_li_xu_lie_lcof {
    public static void main(String[] args) {
        int[] arr = {1,3,2,6,5};
        System.out.println(verifyPostorder2(arr));
    }

    // 递归+区间划分
    public static boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    public static boolean recur(int[] postorder, int start, int end) {

        if (start > end) {
            return true;
        }

        int p = start;
        while (postorder[p] < postorder[end]) {
            p++;
        }
        int s0 = p - 1;
        // [start, p-1]
        while (postorder[p] > postorder[end]) {
            p++;
        }
        return p == end && recur(postorder, start, s0) && recur(postorder, s0 + 1, end - 1);
    }

    //辅助单调栈
    // 1,3,2,6,5 -> 5,6,2,3,1，访问变成根，右，左，满足arr[i]>arr[i-1]则一直入栈，
    // 如果arr[i]<arr[i-1]表明arr[i]为左数的节点，则一直弹出节点，并且栈底为根节点，
    public static boolean verifyPostorder2(int[] postorder) {

        Stack<Integer> stack = new Stack<>();

        int root = Integer.MAX_VALUE;

        for (int i = postorder.length - 1; i >= 0 ; i--) {
            // 相对于上个根节点，遍历的是左子树的节点，所以一定要比根节点小
            if (postorder[i] > root) {
                return false;
            }
            // 碰到左节点则弹出，弹出的是子树的右节点，最后一个是当前子树的根节点
            while (!stack.isEmpty() && postorder[i] < stack.peek()) {
                root = stack.pop();
            }
            stack.push(postorder[i]);
        }

        return true;
    }

}
