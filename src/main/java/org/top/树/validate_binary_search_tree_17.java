package org.top.树;

import org.top.common.TreeNode;

import java.util.Stack;

//https://leetcode-cn.com/problems/validate-binary-search-tree
//验证二叉搜索树
/*
给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
有效 二叉搜索树定义如下：
    节点的左子树只包含 小于 当前节点的数。
    节点的右子树只包含 大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。

输入：root = [2,1,3]
输出：true

输入：root = [5,1,4,null,null,3,6]
输出：false
解释：根节点的值是 5 ，但是右子节点的值是 4 。

提示：

    树中节点数目范围在[1, 10^4] 内
    -2^31 <= Node.val <= 2^31 - 1


 */
public class validate_binary_search_tree_17 {
    public static void main(String[] args) {

    }

    // Integer.MIN_VALUE不行的时候试试Long.MIN_VALUE
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

    boolean ans = true;
    public boolean isValidBST3(TreeNode root) {
        f(root);
        return ans;

    }

    private void f(TreeNode root) {
        if (root == null) {
            return;
        }
        f(root.left);
        if (root.val <= pre) {
            ans = false;
            return;
        }
        pre = root.val;
        f(root.right);
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
