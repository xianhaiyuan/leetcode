package org.top.树;

import org.top.common.TreeNode;

//删除二叉搜索树中的节点
//https://leetcode-cn.com/problems/delete-node-in-a-bst
/*
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
一般来说，删除节点可分为两个步骤：
    首先找到需要删除的节点；
    如果找到了，删除它。

输入：root = [5,3,6,2,4,null,7], key = 3
输出：[5,4,6,2,null,null,7]
解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
另一个正确答案是 [5,2,6,null,4,null,7]。

输入: root = [5,3,6,2,4,null,7], key = 0
输出: [5,3,6,2,4,null,7]
解释: 二叉树不包含值为 0 的节点

输入: root = [], key = 0
输出: []

提示:

    节点数的范围 [0, 104].
    -10^5 <= Node.val <= 10^5
    节点值唯一
    root 是合法的二叉搜索树
    -10^5 <= key <= 10^5

进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 */
public class delete_node_in_a_bst_9 {
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
