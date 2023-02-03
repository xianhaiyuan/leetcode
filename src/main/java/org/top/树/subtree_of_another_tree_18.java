package org.top.树;

import org.top.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//https://leetcode-cn.com/problems/subtree-of-another-tree
//另一个树的子树
/*
给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。

输入：root = [3,4,5,1,2], subRoot = [4,1,2]
输出：true

输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
输出：false

提示：

    root 树上的节点数量范围是 [1, 2000]
    subRoot 树上的节点数量范围是 [1, 1000]
    -10^4 <= root.val <= 10^4
    -10^4 <= subRoot.val <= 10^4

 */
public class subtree_of_another_tree_18 {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);
    }

    private boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        return check(root, subRoot) || dfs(root.left, subRoot) || dfs(root.right, subRoot);
    }


    public boolean check(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.val == subRoot.val) {
            return check(root.left, subRoot.left) && check(root.right, subRoot.right);
        }
        return false;
    }

    // 深度优先搜索序列上做串匹配
    // 我们可以引入两个空值 lNull 和 rNull，当一个节点的左孩子或者右孩子为空的时候，就插入这两个空值，这样深度优先搜索序列就唯一对应一棵树。
    // 在判断「s 的深度优先搜索序列包含 t 的深度优先搜索序列」的时候，可以暴力匹配，也可以使用 KMP 算法。
    List<Integer> sOrder = new ArrayList<>();
    List<Integer> tOrder = new ArrayList<>();
    int maxElement, lNull, rNull;

    public boolean isSubtree1(TreeNode s, TreeNode t) {
        maxElement = Integer.MIN_VALUE;
        getMaxElement(s);
        getMaxElement(t);
        // 防止元素重复
        lNull = maxElement + 1;
        rNull = maxElement + 2;

        getDfsOrder(s, sOrder);
        getDfsOrder(t, tOrder);

        // 也可以用s.contains(t)或s.index(t,0) != -1
        return kmp();

    }

    private void getDfsOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        if (root.left != null) {
            getDfsOrder(root.left, list);
        } else {
            list.add(lNull);
        }

        if (root.right != null) {
            getDfsOrder(root.right, list);
        } else {
            list.add(rNull);
        }
    }

    private void getMaxElement(TreeNode s) {
        if (s == null) {
            return;
        }
        maxElement = Math.max(maxElement, s.val);
        getMaxElement(s.left);
        getMaxElement(s.right);
    }

    public static void main(String[] args) {

    }

    private boolean kmp() {
        int sLen = sOrder.size(), tLen = tOrder.size();
        int[] next = new int[tOrder.size()];
        Arrays.fill(next, -1);


        for (int i = 1, j = -1; i < tLen; i++) {

            while (j != -1 && !(tOrder.get(i).equals(tOrder.get(j+1)))) {
                j = next[j];
            }
            if (tOrder.get(i).equals(tOrder.get(j+1))) {
                j++;
            }
            next[i] = j;
        }

        for (int i = 0, j = -1; i < sLen; i++) {
            while (j != -1 && !(sOrder.get(i).equals(tOrder.get(j+1)))) {
                j = next[j];
            }
            if (sOrder.get(i).equals(tOrder.get(j+1))) {
                j++;
            }
            if (j == tLen - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean kmp1(char[] s, char[] t) {

        int[] next = new int[t.length];
        Arrays.fill(next, -1);
        for (int i = 1, j = -1; i < t.length; i++) {
            while (j != -1 && t[i] != t[j+1]) {
                j = next[j];
            }
            if (t[i] == t[j+1]) {
                j++;
            }
            next[i] = j;
        }

        for (int i = 0, j = -1; i < s.length; i++) {
            while (j != -1 && s[i] != t[j+1]) {
                j = next[j];
            }
            if (s[i] == t[j+1]) {
                j++;
            }
            if (j == t.length - 1) {
                return true;
            }
        }

        return false;
    }

    public boolean kmp2(char[] s, char[] m) {

        if (s == null || m == null || s.length < 1 || s.length < m.length) {
            return false;
        }
        int x = 0, y = 0;
        int[] next = getNextArr(m);

        while (x < s.length && y < m.length) {
            if (s[x] == m[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        // 字串位置：x - y
        return y == m.length;

    }

    // 获取next数组
    // next 数组的值是除当前字符外（注意不包括当前字符）的公共前后缀最长长度
    //  a  a  b  a  a  b  c
    // -1  0  1  0  1  2  3
    //  a  a  b  a  a  b  a  a  b  c
    public int[] getNextArr(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (match[i-1] == match[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

}
