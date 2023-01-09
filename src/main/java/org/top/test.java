package org.top;

import org.top.common.Node;
import org.top.common.TreeNode;

import java.util.*;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    // abcabcbb
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int max = Integer.MIN_VALUE;
        int pos = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0;
        while (l < s.length() && r < s.length()) {
            Integer index = map.get(s.charAt(r));
            if (index == null) {
                if (r - l + 1 > max) {
                    max = r - l + 1;
                }
                map.put(s.charAt(r), r);
                r++;
            } else {
                l = Math.max(index + 1, pos);
                if (r - l + 1 > max) {
                    max = r - l + 1;
                }
                pos = l;
                map.put(s.charAt(r), r);
                r++;
            }
        }

        return max;
    }


}
