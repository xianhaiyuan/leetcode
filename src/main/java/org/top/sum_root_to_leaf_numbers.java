package org.top;

import org.top.common.TreeNode;

//https://leetcode.cn/problems/sum-root-to-leaf-numbers/
//求根节点到叶节点数字之和
public class sum_root_to_leaf_numbers {
    public static void main(String[] args) {

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(0);

        n1.left = n2;

        System.out.println(sumNumbers(n1));

    }
    public static int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    public static int sum(TreeNode root, int s) {
        if (root == null) {
            return 0;
        }
        int cur = s * 10 + root.val;
        if (root.left == null && root.right == null) {
            return cur;
        }
        return sum(root.left, cur) + sum(root.right, cur);
    }
}
