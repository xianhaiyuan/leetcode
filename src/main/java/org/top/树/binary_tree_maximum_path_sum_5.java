package org.top.树;
import org.top.common.TreeNode;

//https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
//二叉树中的最大路径和
/*
路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，
且不一定经过根节点。
路径和 是路径中各节点值的总和。
给你一个二叉树的根节点 root ，返回其 最大路径和 。

输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6

输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42

提示：

    树中节点数目范围是 [1, 3 * 104]
    -1000 <= Node.val <= 1000

 */
public class binary_tree_maximum_path_sum_5 {
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

