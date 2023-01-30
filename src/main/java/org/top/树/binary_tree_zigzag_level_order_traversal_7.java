package org.top.树;

import org.top.common.TreeNode;

import java.util.*;

//二叉树的锯齿形层次遍历
//https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
/*
给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

输入：root = [3,9,20,null,null,15,7]
输出：[[3],[20,9],[15,7]]

输入：root = [1]
输出：[[1]]

输入：root = []
输出：[]

提示：

    树中节点数目在范围 [0, 2000] 内
    -100 <= Node.val <= 100

 */
public class binary_tree_zigzag_level_order_traversal_7 {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        List<List<Integer>> lists = zigzagLevelOrder(n1);
        System.out.println(lists.size());

    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> deQueue = new ArrayDeque<>();
        deQueue.offer(root);
        boolean isLeft = true;
        while (!deQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = deQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deQueue.poll();
                if (isLeft) {
                    levelList.offerLast(node.val);
                } else {
                    levelList.offerFirst(node.val);
                }

                if (node.left != null) {
                    deQueue.offer(node.left);
                }
                if (node.right != null) {
                    deQueue.offer(node.right);
                }

            }
            res.add(new ArrayList<>(levelList));
            isLeft = !isLeft;

        }
        return res;
    }
}
