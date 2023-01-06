package org.top;

import org.top.common.TreeNode;

//删除二叉搜索树中的节点
//https://leetcode-cn.com/problems/delete-node-in-a-bst
public class delete_node_in_a_bst {
    public static void main(String[] args) {


    }

    /*
    如果目标节点大于当前节点值，则去右子树中删除；
    如果目标节点小于当前节点值，则去左子树中删除；
    如果目标节点就是当前节点，分为以下三种情况：

        其无左子：其右子顶替其位置，删除了该节点；
        其无右子：其左子顶替其位置，删除了该节点；
        其左右子节点都有：其左子树转移到其右子树的最左节点的左子树上，然后右子树顶替其位置，由此删除了该节点。

    作者：Terry
    链接：https://leetcode.cn/problems/delete-node-in-a-bst/solutions/582561/miao-dong-jiu-wan-shi-liao-by-terry2020-tc0o/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 要删除的节点的左右子节点都存在
            TreeNode tmp = root.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            tmp.left = root.left;
            root = root.right;
        }
        return root;
    }
}
