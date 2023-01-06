package org.top;
import org.top.common.TreeNode;

//https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
//二叉树中的最大路径和
public class binary_tree_maximum_path_sum {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        int res = maxPathSum(treeNode);
        System.out.println(res);
    }

    private static int MAX_SUM = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        process(root);
        return MAX_SUM;
    }
    public static int process(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMax = Math.max(process(root.left), 0);
        int rightMax = Math.max(process(root.right), 0);

        MAX_SUM = Math.max(MAX_SUM, leftMax + rightMax + root.val);

        return Math.max(leftMax, rightMax) + root.val;
    }
}

