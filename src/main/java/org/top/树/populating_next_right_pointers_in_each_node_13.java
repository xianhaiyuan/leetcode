package org.top.树;

import org.top.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.cn/problems/populating-next-right-pointers-in-each-node
//填充每个节点的下一个右侧节点指针
/*
给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

输入：root = [1,2,3,4,5,6,7]
输出：[1,#,2,3,#,4,5,6,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。

输入：root = []
输出：[]

提示：

    树中节点的数量在 [0, 2^12 - 1] 范围内
    -1000 <= node.val <= 1000

进阶：

    你只能使用常量级额外空间。
    使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。

 */
public class populating_next_right_pointers_in_each_node_13 {

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

    public TreeNode connect1(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            root.left.next = root.right;
            root.right.next = root.next != null ? root.next.left : null;
            connect1(root.left);
            connect1(root.right);
        }
        return root;
    }

}
