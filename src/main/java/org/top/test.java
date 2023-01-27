package org.top;

import org.top.common.Node;
import org.top.common.TreeNode;

import java.util.*;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {
        String res = longestPalindrome("cbbd");
        System.out.println(res);
    }

    public static String longestPalindrome(String s) {

        if (s == null || s.length() == 0) {
            return s;
        }

        List<Integer> arm_len = new ArrayList<>();
        StringBuilder t = new StringBuilder("#");

        for (int i = 0; i < s.length(); i++) {
            t.append(s.charAt(i)).append("#");
        }
        t.append("#");
        s = t.toString();

        int start = -1, end = -1;
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); i++) {
            int cur_arm_len;
            if (right >= i) {
                int i1 = 2*j-i;
                int min_arm_len = Math.min(arm_len.get(i1), right - j);
                cur_arm_len = expend(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expend(s, i, i);
            }

            arm_len.add(cur_arm_len);

            if (i + cur_arm_len > right) {
                right = i + cur_arm_len;
                j = i;
            }

            if (2 * cur_arm_len + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }

        }


        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) != '#') {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();

    }

    public static int expend(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return (right - left - 2) / 2;
    }


}
