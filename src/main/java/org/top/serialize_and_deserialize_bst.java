package org.top;

import org.top.common.TreeNode;

import java.util.*;

public class serialize_and_deserialize_bst {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;

        String serialize = serialize(n1);
        System.out.println(serialize);

        TreeNode deserialize = deserialize(serialize);
        System.out.println(deserialize.val);
    }

    public static String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        String s = list.toString();
        return s.substring(1, s.length()-1);
    }

    public static void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }


    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }

        String[] split = data.split(", ");
        Deque<Integer> stack = new LinkedList<>();
        // 使用栈压进去之后，弹出来的顺序就是 根，右，左
        for (int i = 0; i < split.length; i++) {
            stack.push(Integer.parseInt(split[i]));
        }

        return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
    }

    private static TreeNode construct(int minValue, int maxValue, Deque<Integer> stack) {
        if (stack.isEmpty() || stack.peek() < minValue || stack.peek() > maxValue) {
            return null;
        }
        // 根，右，左
        int val = stack.pop();
        TreeNode node = new TreeNode(val);
        node.right = construct(val, maxValue, stack);
        node.left = construct(minValue, val, stack);

        return node;
    }

}
