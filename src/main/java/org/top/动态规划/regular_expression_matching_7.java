package org.top.动态规划;
//https://leetcode.cn/problems/regular-expression-matching/
//正则表达式匹配
/*
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

    '.' 匹配任意单个字符
    '*' 匹配零个或多个前面的那一个元素

所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

输入：s = "aa", p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。

输入：s = "aa", p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

输入：s = "ab", p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

提示：

    1 <= s.length <= 20
    1 <= p.length <= 30
    s 只包含从 a-z 的小写字母。
    p 只包含从 a-z 的小写字母，以及字符 . 和 *。
    保证每次出现字符 * 时，前面都匹配到有效的字符

 */
public class regular_expression_matching_7 {
    public static void main(String[] args) {
        String s = "aaa", p = "a*a";

        System.out.println(isMatch(s, p));

    }

    //虽然题目没有写，但是开头*或者连续*都是非法用例
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m+1][n+1];
        f[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //虽然题目没有写，但是开头*或者连续*都是非法用例，并且s中有.或*都是非法
                if (p.charAt(j-1) == '*') {
                    // *把前面一个字符消掉了，这里直接赋值，不用对比，下面考虑可能性使用||得出最终答案
                    f[i][j] = f[i][j-2];
                    if (match(s, i, p, j-1)) {
                        // 因为f[i-1][j]也是推导过来的，所以包含了之前的答案
                        f[i][j] = f[i][j] || f[i-1][j];
                    }
                } else {
                    if (match(s, i, p, j)) {
                        f[i][j] = f[i-1][j-1];
                    }
                }
            }
        }

        return f[m][n];
    }

    public static boolean match(String s, int i, String p, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j-1) == '.') {
            return true;
        }
        if (s.charAt(i-1) == p.charAt(j-1)) {
            return true;
        }
        return false;
    }


    public static boolean dfs(String s, int i, String p, int j) {
        if (p.length() == j) {
            return i == s.length();
        }

        if (j+1 == p.length() || p.charAt(j+1) != '*') {
            return s.length() != i && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') && dfs(s, i+1, p, j+1);
        }

        while (i != s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
            if (dfs(s, i, p, j+2)) {
                return true;
            }
            i++;
        }

        return dfs(s, i, p, j+2);

    }


}
