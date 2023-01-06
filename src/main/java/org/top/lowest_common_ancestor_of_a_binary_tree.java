package org.top;
import org.top.common.TreeNode;

import java.util.*;

// 二叉树的最近公共祖先
// https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
public class lowest_common_ancestor_of_a_binary_tree {
    public static void main(String[] args) {

        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(6);
        TreeNode n5 = new TreeNode(2);
        TreeNode n6 = new TreeNode(0);
        TreeNode n7 = new TreeNode(8);
        TreeNode n10 = new TreeNode(7);
        TreeNode n11 = new TreeNode(4);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n10;
        n5.right = n11;
        n3.left = n6;
        n3.right = n7;

        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);


        TreeNode res = func3(n1, p, q);
        System.out.println(res.val);


    }

    private static TreeNode res;


    // 递归
    public static TreeNode func(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    public static boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);

        if (lson && rson || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            res = root;
        }

        return lson || rson || (root.val == p.val || root.val == q.val);

    }


    private static Map<Integer, TreeNode> parentMap = new HashMap<>();
    private static Set<Integer> visited = new HashSet<>();

    // 通过map和set
    public static TreeNode func2(TreeNode root, TreeNode p, TreeNode q) {
        dfs2(root);

        while (p != null) {
            visited.add(p.val);
            p = parentMap.get(p.val);
        }

        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parentMap.get(q.val);
        }
        return null;
    }

    public static void dfs2(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            dfs2(root.left);
        }

        if (root.right != null) {
            parentMap.put(root.right.val, root);
            dfs2(root.right);
        }
    }

    // 递归2
    public static TreeNode func3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode n1 = func3(root.left, p, q);
        TreeNode n2 = func3(root.right, p, q);

        if (n1 == null && n2 == null) {
            return null;
        }

        if (n1 == null) {
            return n2;
        }

        if (n2 == null) {
            return n1;
        }
        return root;
    }
}
