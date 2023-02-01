package org.top.滑动窗口;

import java.util.*;

//https://leetcode.cn/problems/minimum-window-substring
//最小覆盖子串
/*
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
注意：

    对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
    如果 s 中存在这样的子串，我们保证它是唯一的答案。

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。

输入：s = "a", t = "a"
输出："a"
解释：整个字符串 s 是最小覆盖子串。

输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。

提示：

    m == s.length
    n == t.length
    1 <= m, n <= 10^5
    s 和 t 由英文字母组成

进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 */
public class minimum_window_substring_2 {
    public static void main(String[] args) {

        System.out.println(minWindow("aa", "aa"));
    }

    public static boolean isAdd(Map<Character, Integer> smap, Map<Character, Integer> tmap, Character c) {
        if (smap.get(c) == null || smap.get(c) < tmap.get(c)) {
            return true;
        }
        return false;
    }

    public static boolean isReduce(Map<Character, Integer> smap, Map<Character, Integer> tmap, Character c) {
        if (smap.get(c) < tmap.get(c)) {
            return true;
        }
        return false;
    }
    public static String minWindow(String s, String t) {

        // 如果smap中包含的字符的数目达到了tmap中的数量，则记录是否为最小值，L++，窗口左边界移动
        Map<Character, Integer> smap = new HashMap<>();

        Map<Character, Integer> tmap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
        }

        int l = 0, r = 0;
        int minContainNum = Integer.MAX_VALUE;
        int minL = -1, minR = -1;
        int containNum = 0;
        while (r < s.length()) {

            Integer n = smap.get(s.charAt(r));
            if (tmap.containsKey(s.charAt(r))) {

                if (isAdd(smap, tmap, s.charAt(r))) {
                    containNum++;
                }
                smap.put(s.charAt(r), (n == null ? 0 : n) + 1);
            }

            while (containNum == t.length() && l <= r) {
                if (r - l < minContainNum) {
                    minContainNum = r - l;
                    minL = l;
                    minR = r;
                }

                Integer num = smap.get(s.charAt(l));
                if (num != null) {
                    if (num - 1 >= 0) {
                        smap.put(s.charAt(l), num-1);
                        if (isReduce(smap, tmap, s.charAt(l))) {
                            containNum--;
                        }
                    }
                }

                l++;

            }

            r++;

        }

        return minR != -1 ? s.substring(minL, minR + 1) : "";


    }
}
