package org.top.树;

import org.top.common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//二叉树的序列化与反序列化
//https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
/*
序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]

输入：root = []
输出：[]

输入：root = [1]
输出：[1]

输入：root = [1,2]
输出：[1,2]

提示：

    树中结点数在范围 [0, 10^4] 内
    -1000 <= Node.val <= 1000

 */
public class serialize_and_deserialize_binary_tree_14 {
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
