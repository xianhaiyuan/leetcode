package org.top.树;

import org.top.common.TreeNode;

//二叉树的直径
//https://leetcode-cn.com/problems/diameter-of-binary-tree
/*
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class diameter_of_binary_tree_10 {

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
