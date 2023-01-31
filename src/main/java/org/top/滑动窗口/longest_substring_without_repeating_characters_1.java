package org.top.滑动窗口;

import java.util.*;

//无重复字符的最长子串
//https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
/*
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

提示：

    0 <= s.length <= 5 * 10^4
    s 由英文字母、数字、符号和空格组成

 */
public class longest_substring_without_repeating_characters_1 {
    public static void main(String[] args) {

        String s = "tmmzuxt";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring1(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;

    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }

        Map<Character, Integer> map = new HashMap<>();

        int left = 0, right = 0;

        int ans = 1;

        while (right < s.length()) {

            if (map.containsKey(s.charAt(right))) {
                // 如果map.get(s.charAt(right)) + 1 < left, 那么一定要取现在的left
                // 比如 "tmmzuxt"，到最后的t，如果left取1，那么m会重复
                left = Math.max(map.get(s.charAt(right)) + 1, left);
            }

            map.put(s.charAt(right), right);

            ans = Math.max(ans, right - left + 1);

            right++;

        }

        return ans;

    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int max = Integer.MIN_VALUE;
        // 记录不重复的位置
        int pos = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0;
        while (l < s.length() && r < s.length()) {
            Integer index = map.get(s.charAt(r));
            // 发现重复
            if (index != null) {
                l = Math.max(index + 1, pos);
                pos = l;
            }
            if (r - l + 1 > max) {
                max = r - l + 1;
            }
            map.put(s.charAt(r), r);
            r++;
        }

        return max;
    }
}
