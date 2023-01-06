package org.top;

import java.util.*;

//无重复字符的最长子串
//https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
public class longest_substring_without_repeating_characters {
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
}
