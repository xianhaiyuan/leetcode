package org.top;

import org.top.common.Node;
import org.top.common.TreeNode;

import java.util.*;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {

    }

    private static int maxVal = Integer.MIN_VALUE;

    public static int func(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int lnum = func(node.left);
        int rnum = func(node.right);

        if (lnum + rnum > maxVal) {
            maxVal = lnum + rnum;
        }

        return Math.max(lnum, rnum) + 1;
    }

}
