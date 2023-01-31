package org.top.深度优先和广度优先;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode-cn.com/problems/generate-parentheses
//括号生成
/*
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]

输入：n = 1
输出：["()"]

提示：

    1 <= n <= 8

 */
public class generate_parentheses_4 {
    public static void main(String[] args) {

        List<String> res = generateParenthesis1(3);
        res.forEach(System.out::println);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
//        backtrack(ans, new StringBuilder(), 0, 0, n);
        backtrack1(ans, "", n, n);
        return ans;
    }

    // 回溯法（系统栈深度优先）
    public static void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == 2 * max) {
            ans.add(new String(cur));
            return;
        }

        if (open < max) {
            cur.append("(");
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }

        if (close < open) {
            cur.append(")");
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }

    }

    // 回溯2（系统栈深度优先）
    public static void backtrack1(List<String> ans, String cur, int open, int close) {
        if (open == 0 && close == 0) {
            ans.add(cur);
            return;
        }
        if (open > close) {
            return;
        }

        if (open > 0) {
            backtrack1(ans, cur + "(", open - 1, close);
        }

        if (close > 0) {
            backtrack1(ans, cur + ")", open, close - 1);
        }

    }

    static class Node {
        String str;
        int open;
        int close;

        public Node(String s, int o, int c) {
            str = s;
            open = o;
            close = c;
        }
    }

    public static List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.open == 0 && cur.close == 0) {
                ans.add(cur.str);
            }
            if (cur.open > 0) {
                queue.offer(new Node(cur.str + "(", cur.open - 1, cur.close));
            }
            if (cur.close > 0 && cur.open < cur.close) {
                queue.offer(new Node(cur.str + ")", cur.open, cur.close - 1));
            }
        }
        return ans;
    }

}
