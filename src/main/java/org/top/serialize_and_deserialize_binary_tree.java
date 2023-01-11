package org.top;

import org.top.common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//二叉树的序列化与反序列化
//https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
public class serialize_and_deserialize_binary_tree {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;


        String serialize = serialize(n1);
        System.out.println(serialize);
        TreeNode root = deserialize(serialize);
        System.out.println(root.val);
    }

    public static String serialize(TreeNode root) {
        return serializeFunc(root, "");

    }

    public static String serializeFunc(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += root.val + ",";
            str = serializeFunc(root.left, str);
            str = serializeFunc(root.right, str);
        }

        return str;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String[] split = data.split(",");
        List<String> list = Arrays.asList(split);
        LinkedList<String> linkedList = new LinkedList<>(list);
        return deserializeFunc(linkedList);
    }

    public static TreeNode deserializeFunc(LinkedList<String> linkedList) {

        if (linkedList == null || "None".equals(linkedList.get(0))) {
            linkedList.remove(0);
            return null;
        }
        String s = linkedList.get(0);
        TreeNode node = new TreeNode(Integer.valueOf(s));
        linkedList.remove(0);
        node.left = deserializeFunc(linkedList);
        node.right = deserializeFunc(linkedList);

        return node;

    }

    static int index = 0;
    public static TreeNode deserializeFunc1(List<String> arr) {
        if (index >= arr.size() || "null".equals(arr.get(index))) {
            index++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(arr.get(index)));
        index++;
        node.left = deserializeFunc1(arr);
        node.right = deserializeFunc1(arr);

        return node;
    }
}
