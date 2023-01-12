package org.top;

import org.top.common.TreeNode;

import java.util.Stack;

//https://leetcode-cn.com/problems/validate-binary-search-tree
//验证二叉搜索树
public class validate_binary_search_tree {
    public static void main(String[] args) {

    }

    long pre = Long.MIN_VALUE;
    // 递归中序遍历
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }

    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }

        long pre = Long.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (root.val <= pre) {
                return false;
            }

            pre = root.val;
            root = root.right;

        }
        return true;

    }


}
