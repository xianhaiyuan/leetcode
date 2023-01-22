package org.top;

import org.top.common.TreeNode;

//https://mp.weixin.qq.com/s/yewlHvHSilMsrUMFIO8WAA
//二叉树的下一个节点
public class binary_tree_next_node {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n4.left = n7;
        n5.left = n8;
        n5.right = n9;
        n3.right = n6;

        n2.parent = n1;
        n3.parent = n1;
        n4.parent = n2;
        n5.parent = n2;
        n7.parent = n4;
        n8.parent = n5;
        n9.parent = n5;
        n6.parent = n3;

        System.out.println(nextNode(n9).val);

    }

    /*
        若x有右子树。则x的下一个节点为x右子树最左侧节点。如，2的下一个节点为8。
        若x没有右子树，又分为2种情况。
        若x是父节点的左孩子。则x的父节点就是x的下一个节点。如，7的下一个节点是4。
        若x是父节点的右孩子。则沿着父节点向上，直到找到一个节点的父节点的左孩子是该节点，则该节点的父节点就是x的下一个节点。如，9的下一个节点是1。
     */

    public static TreeNode nextNode(TreeNode node) {
        if (node.right != null) {

            TreeNode p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;

        } else {

            TreeNode p = node;
            while (p.parent != null) {
                if (p.parent.left == p) {
                    return p.parent;
                }
                p = p.parent;
            }

        }
        return null;
    }
}
