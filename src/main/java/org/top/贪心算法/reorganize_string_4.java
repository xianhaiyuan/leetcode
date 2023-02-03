package org.top.贪心算法;

import java.util.PriorityQueue;

//https://leetcode.cn/problems/reorganize-string/
//重构字符串
/*
给定一个字符串 s ，检查是否能重新排布其中的字母，使得两相邻的字符不同。
返回 s 的任意可能的重新排列。若不可行，返回空字符串 "" 。

输入: s = "aab"
输出: "aba"

输入: s = "aaab"
输出: ""

提示:

    1 <= s.length <= 500
    s 只包含小写字母

 */
public class reorganize_string_4 {

    // 用字符出现的次数建立堆，每次取出次数出现最多和次多的字符append
    public String reorganizeString(String s) {
        if (s.length() < 2) {
            return s;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            counts[c-'a']++;
            maxCount = Math.max(maxCount, counts[c-'a']);
        }

        // aaab, aaabb(ababa)
        if (maxCount > (length + 1) / 2) {
            return "";
        }

        PriorityQueue<Character> q = new PriorityQueue<>((c1, c2) -> counts[c2-'a'] - counts[c1-'a']);

        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                q.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        // 每次取出两个，>1 防止空指针
        while (q.size() > 1) {
            Character c1 = q.poll();
            Character c2 = q.poll();
            sb.append(c1);
            sb.append(c2);

            int index1 = c1 - 'a', index2 = c2 - 'a';
            counts[index1]--;
            counts[index2]--;

            if (counts[index1] > 0) {
                q.offer(c1);
            }

            if (counts[index2] > 0) {
                q.offer(c2);
            }
        }

        if (q.size() > 0) {
            sb.append(q.poll());
        }
        return sb.toString();

    }
}
