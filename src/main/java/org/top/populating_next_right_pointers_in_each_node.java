package org.top;

import org.top.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.cn/problems/populating-next-right-pointers-in-each-node
//填充每个节点的下一个右侧节点指针
public class populating_next_right_pointers_in_each_node {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        TreeNode root = connect(n1);
        System.out.println(root.val);

    }

    public static TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            root.next = null;
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int num = 1;
        TreeNode pre;

        while (!queue.isEmpty()) {
            TreeNode node = null;
            int tmp = 0;
            pre = null;
            for (int i = 0; i < num; i++) {
                 node = queue.poll();
                if (pre != null) {
                    pre.next = node;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    tmp++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    tmp++;
                }
                pre = node;
            }
            node.next = null;
            num = tmp;
        }

        return root;

    }

}
