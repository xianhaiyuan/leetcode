package org.top.动态规划;

import java.util.*;

//https://leetcode.cn/problems/longest-palindromic-substring
//最长回文子串
/*
给你一个字符串 s，找到 s 中最长的回文子串。
如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。

输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。

输入：s = "cbbd"
输出："bb"

提示：

    1 <= s.length <= 1000
    s 仅由数字和英文字母组成

 */
public class longest_palindromic_substring_5 {

    public static void main(String[] args) {
        String res = longestPalindrome1("babad");
        System.out.println(res);
    }

    //Manacher 马拉车算法
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        //dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int str_len = 2; str_len <= len; str_len++) {
            for (int start = 0; start <= len - 2; start++) {
                int end = start + str_len - 1;
                if (end >= len) {
                    break;
                }

                if (s.charAt(start) != s.charAt(end)) {
                    dp[start][end] = false;
                } else {
                    if (end - start < 2) {
                        dp[start][end] = true;
                    } else {
                        dp[start][end] = dp[start+1][end-1];
                    }
                }

                if (dp[start][end] && end - start + 1 > maxLen) {
                    maxLen = end - start + 1;
                    begin = start;
                }

            }

        }
        return s.substring(begin, begin + maxLen);
    }

    public static String longestPalindrome1(String s) {

        if (s == null || s.length() == 0) {
            return s;
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expend(s, i, i);
            int len2 = expend(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (end - start < len) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);

    }

    private static int expend(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        // aba
        // abba
        return right - left - 1;
    }

    public String longestPalindrome2(String s) {
        int start = 0, end = -1;
        StringBuilder t = new StringBuilder("#");
        for (int i = 0; i < s.length(); i++) {
            t.append(s.charAt(i)).append("#");
        }
        t.append("#");
        s = t.toString();

        List<Integer> arm_len = new ArrayList<>();
        // j是最大扩展的下标，right是最大扩展的右边界
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); i++) {
            // cur_arm_len不包括i
            // #a#b#c#b#a# -> 10/2 = 5
            // #a#b#b#a# -> 9/2 = 4
            int cur_arm_len;
            // 如果在曾经最大扩展的范围内
            if (i <= right) { // <= 容易写成 <
                // i的对称点
                int i_sym = j*2 - i;
                // 扩的时候不能找过边界（易漏点）
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expend1(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expend1(s, i, i);
            }
            arm_len.add(cur_arm_len);
            // 更新最大扩展
            if (i + cur_arm_len > right) {
                right = i + cur_arm_len;
                j = i;
            }
            // 更新答案
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public static String longestPalindroe2(String s) {


        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append("#");
        }
        sb.append("#");

        s = sb.toString();

        int n = sb.length();

        int[] f = new int[n];

        int j = -1, r = -1;
        int maxExpend = -1;
        int begin = -1, end = -1;

        for (int i = 0; i < n; i++) {
            int expend;
            if (i <= r) {
                int sym_i = 2 * j - i;
                int tmp = Math.min(f[sym_i], r - i);
                expend = expend(s, i - tmp, i + tmp);
            } else {
                expend = expend(s, i, i);
            }

            f[i] = expend;

            if (i + expend > r) {
                r = i + expend;
                j = i;
            }
            if (expend > maxExpend) {
                maxExpend = expend;
                begin = i - expend;
                end = i + expend;
            }
        }

        sb = new StringBuilder();
        for (int i = begin; i <= end; i++) {
            if (s.charAt(i) != '#') {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private int expend1(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        // -2是因为最后多了一次--left和++right
        // #a#b#c#b#a# -> 10/2 = 5
        // #a#b#b#a# -> 9/2 = 4
        return (right - left - 2) / 2;
    }
}
