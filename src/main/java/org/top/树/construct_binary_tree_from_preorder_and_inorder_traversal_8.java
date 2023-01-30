package org.top.树;

import org.top.common.TreeNode;
import java.util.*;

//https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
//从前序与中序遍历序列构造二叉树
/*
给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
输出: [3,9,20,null,null,15,7]

输入: preorder = [-1], inorder = [-1]
输出: [-1]

提示:

    1 <= preorder.length <= 3000
    inorder.length == preorder.length
    -3000 <= preorder[i], inorder[i] <= 3000
    preorder 和 inorder 均 无重复 元素
    inorder 均出现在 preorder
    preorder 保证 为二叉树的前序遍历序列
    inorder 保证 为二叉树的中序遍历序列

 */
public class construct_binary_tree_from_preorder_and_inorder_traversal_8 {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        TreeNode node = buildTree(preorder, inorder);
        System.out.println(node.val);
    }

    private static Map<Integer, Integer> indexMap;
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        //preorder = [3,9,20,15,7],
        //inorder = [9,3,15,20,7]
        //[3,9,20,null,null,15,7]

        int n = preorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return build(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    public static TreeNode build(int[] preorder, int preorder_left, int preorder_right,
                                 int[] inorder, int inorder_left, int inorder_right) {

        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        TreeNode root = new TreeNode(preorder[preorder_root]);

        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;

        root.left = build(preorder, preorder_left + 1, preorder_left + size_left_subtree,
                inorder, inorder_left, inorder_root - 1);

        root.right = build(preorder, preorder_left + size_left_subtree + 1, preorder_right,
                inorder, inorder_root + 1, inorder_right);

        return root;
    }



}
