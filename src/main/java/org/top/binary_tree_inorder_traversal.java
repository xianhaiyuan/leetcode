package org.top;

import org.top.common.TreeNode;

import java.util.*;

//https://leetcode-cn.com/problems/binary-tree-inorder-traversal
//二叉树的中序遍历
public class binary_tree_inorder_traversal {
    public static void main(String[] args) {


    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        func(root, ans);
        return ans;
    }

    public void func(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        func(root.left, ans);
        ans.add(root.val);
        func(root.right, ans);
    }
}
