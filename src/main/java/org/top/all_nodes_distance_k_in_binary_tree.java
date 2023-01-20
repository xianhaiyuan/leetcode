package org.top;

import org.top.common.TreeNode;

import java.util.*;

//https://leetcode.cn/problems/all-nodes-distance-k-in-binary-tree
//二叉树中所有距离为 K 的结点
public class all_nodes_distance_k_in_binary_tree {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(6);
        TreeNode n5 = new TreeNode(2);
        TreeNode n6 = new TreeNode(0);
        TreeNode n7 = new TreeNode(8);
        TreeNode n8 = new TreeNode(7);
        TreeNode n9 = new TreeNode(4);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n5.left = n8;
        n5.right = n9;

        List<Integer> ans = distanceK(n1, n2, 2);
        System.out.println(ans);

    }
    static Map<Integer, TreeNode> parentMap = new HashMap<>();
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParent(root);
        List<Integer> ans = new ArrayList<>();
        find(target, null, ans, 0, k);
        return ans;
    }

    public static void find(TreeNode target, TreeNode from, List<Integer> ans, int dept, int k) {
        if (target == null) {
            return;
        }
        if (dept == k) {
            ans.add(target.val);
            return;
        }
        // 使用了parent就需要注意from节点是否和当前节点一样
        if (target.left != from) {
            find(target.left, target, ans, dept + 1, k);
        }
        if (target.right != from) {
            find(target.right, target, ans, dept + 1, k);
        }
        TreeNode p = parentMap.get(target.val);
        if (p != from) {
            find(p, target, ans, dept + 1, k);
        }
    }

    public static void findParent(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            findParent(root.left);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
            findParent(root.right);
        }
    }

}
