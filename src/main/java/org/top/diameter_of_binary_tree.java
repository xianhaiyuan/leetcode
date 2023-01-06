package org.top;

import org.top.common.TreeNode;

//二叉树的直径
//https://leetcode-cn.com/problems/diameter-of-binary-tree
public class diameter_of_binary_tree {

    private static int maxVal = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);

        n1.left = n2;

        func(n1);
        System.out.println(maxVal);

    }

    public static int func(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int lnum = func(node.left);
        int rnum = func(node.right);

        if (lnum + rnum > maxVal) {
            maxVal = lnum + rnum;
        }

        return Math.max(lnum, rnum) + 1;

    }
}
