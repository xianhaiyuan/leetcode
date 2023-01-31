package org.top.树;
import org.top.common.TreeNode;

import java.util.*;

// 二叉树的最近公共祖先
// https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
/*
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。

输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。

输入：root = [1,2], p = 1, q = 2
输出：1

提示：

    树中节点数目在范围 [2, 10^5] 内。
    -10^9 <= Node.val <= 10^9
    所有 Node.val 互不相同 。
    p != q
    p 和 q 均存在于给定的二叉树中。

 */
public class lowest_common_ancestor_of_a_binary_tree_12 {
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


        TreeNode res = lowestCommonAncestor(n1, p, q);
        System.out.println(res.val);


    }

    private static TreeNode res;


    // 递归
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
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
    public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode n1 = lowestCommonAncestor3(root.left, p, q);
        TreeNode n2 = lowestCommonAncestor3(root.right, p, q);

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
