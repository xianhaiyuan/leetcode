package org.top.字符处理;
//https://leetcode.cn/problems/repeated-substring-pattern
//重复的子字符串
/*
给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。

输入: s = "abab"
输出: true
解释: 可由子串 "ab" 重复两次构成。

输入: s = "aba"
输出: false

输入: s = "abcabcabcabc"
输出: true
解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)

提示：

    1 <= s.length <= 10^4
    s 由小写英文字母组成

 */
public class repeated_substring_pattern_3 {
    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abab"));
    }
    public static boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }

    public boolean repeatedSubstringPattern1(String s) {
        return (s+s).indexOf(s, 1) != s.length();
    }
}
