package org.top.树;

import org.top.common.TreeNode;
import java.util.*;
//https://leetcode.cn/problems/binary-search-tree-iterator
//二叉搜索树迭代器
public class binary_search_tree_iterator_3 {
    List<Integer> arr;
    int index;

    public binary_search_tree_iterator_3(TreeNode root) {
        arr = new ArrayList<>();
        index = 0;
        inorder(root);
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        arr.add(root.val);
        inorder(root.right);
    }

    public int next() {
        return arr.get(index++);
    }

    public boolean hasNext() {
        return index < arr.size();
    }
}
