package org.top;

import org.top.common.Node;
import org.top.common.TreeNode;

import java.util.*;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        TreeNode node = buildTree(preorder, inorder);
        System.out.println(node.val);
    }

    static Map<Integer, Integer> map;
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, inorder, 0, n - 1, 0, n - 1);

    }


    public static TreeNode build(int[] preorder, int[] inorder, int pre_left, int pre_right,
                                 int in_left, int in_right) {

        if (pre_left > pre_right) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[pre_left]);

        int in_root = map.get(preorder[pre_left]);

        int left_size = in_root - in_left;

        root.left = build(preorder, inorder, pre_left + 1, pre_left + left_size, in_left,
                in_root - 1);


        root.right = build(preorder, inorder, pre_left + left_size + 1, pre_right, in_root + 1, in_right);

        return root;

    }

}
