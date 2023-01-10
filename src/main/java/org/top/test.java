package org.top;

import org.top.common.Node;
import org.top.common.TreeNode;

import java.util.*;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {

        String s = " ab  cd ";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        s = trim(s);
        s = reverse(s);
        s = reverseEveryWord(s);
        return s;
    }

    private static String reverseEveryWord(String s) {
        String[] arr = s.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(reverse(arr[i]));
        }
        return String.join(" ", list);
    }

    private static String reverse(String s) {
        int l = 0, r = s.length() - 1;
        StringBuilder sb = new StringBuilder(s);
        while (l < r) {
            char tmp = sb.charAt(l);
            sb.setCharAt(l, sb.charAt(r));
            sb.setCharAt(r, tmp);
            l++;
            r--;
        }
        return sb.toString();
    }

    public static String trim(String s) {
        int left = 0, right = s.length() - 1;

        while (left <= right) {
            if (s.charAt(left) != ' ') {
                break;
            }
            left++;
        }

        while (left <= right) {
            if (s.charAt(right) != ' ') {
                break;
            }
            right--;
        }

        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            if (s.charAt(left) == ' ' && sb.charAt(sb.length() - 1) == ' ') {
                left++;
                continue;
            }
            sb.append(s.charAt(left++));
        }

        return sb.toString();

    }


}
