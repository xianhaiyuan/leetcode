package org.top.树;

import org.top.common.TreeNode;

import java.util.*;
//https://leetcode-cn.com/problems/serialize-and-deserialize-bst
//序列化和反序列化二叉搜索树
/*
序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
编码的字符串应尽可能紧凑。

输入：root = [2,1,3]
输出：[2,1,3]

输入：root = []
输出：[]

提示：

    树中节点数范围是 [0, 10^4]
    0 <= Node.val <= 10^4
    题目数据 保证 输入的树是一棵二叉搜索树。

 */
public class serialize_and_deserialize_bst_15 {

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


    // ------------------------------------------------------
    public static String serialize1(TreeNode root) {
        return serializeFunc1(root, "");
    }

    private static String serializeFunc1(TreeNode root, String s) {
        if (root == null) {
            s += "null,";
            return s;
        }
        String left = serializeFunc1(root.left, s);
        String right = serializeFunc1(root.right, left);
        right += root.val + ",";
        return right;
    }

    public static TreeNode deserialize1(String data) {
        String[] split = data.split(",");
        List<String> list = Arrays.asList(split);

        LinkedList<String> linkedList = new LinkedList<>(list);
        return deserializeFunc1(linkedList);
    }

    private static TreeNode deserializeFunc1(LinkedList<String> list) {
        String s = list.removeLast();
        if (s.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(s));

        TreeNode right = deserializeFunc1(list);
        node.right = right;
        TreeNode left = deserializeFunc1(list);
        node.left = left;

        return node;
    }

}
