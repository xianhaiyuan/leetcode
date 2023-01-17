package org.top;
//https://leetcode.cn/problems/regular-expression-matching/
//正则表达式匹配
public class regular_expression_matching {
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
                    f[i][j] = f[i][j-2];
                    if (match(s, i, p, j-1)) {
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
