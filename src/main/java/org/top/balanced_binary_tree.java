package org.top;

import org.top.common.TreeNode;

//https://leetcode-cn.com/problems/balanced-binary-tree
//平衡二叉树
public class balanced_binary_tree {
    public static void main(String[] args) {
        TreeNode r = new TreeNode(1);
        boolean balanced = isBalanced(r);
        System.out.println(balanced);
    }

    public static boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }
        int lh = height(root.left);
        int rh = height(root.right);

        return Math.abs(lh - rh) <= 1 && isBalanced1(root.left) && isBalanced1(root.right);
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        height(root);
        return ans;
    }

    static boolean ans = true;

    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);

        if (Math.abs(lh - rh) > 1) {
            ans = false;
        }

        return Math.max(lh, rh) + 1;
    }
}
