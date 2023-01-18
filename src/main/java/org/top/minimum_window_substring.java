package org.top;

import java.util.*;

//https://leetcode.cn/problems/minimum-window-substring
//最小覆盖子串
public class minimum_window_substring {
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
