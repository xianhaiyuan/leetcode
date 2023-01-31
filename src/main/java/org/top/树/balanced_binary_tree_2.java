package org.top.树;

import org.top.common.TreeNode;

//https://leetcode-cn.com/problems/balanced-binary-tree
//平衡二叉树
/*
给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。

输入：root = [3,9,20,null,null,15,7]
输出：true

输入：root = [1,2,2,3,3,null,null,4,4]
输出：false

输入：root = []
输出：true

提示：

    树中的节点数在范围 [0, 5000] 内
    -10^4 <= Node.val <= 10^4


 */
public class balanced_binary_tree_2 {
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
