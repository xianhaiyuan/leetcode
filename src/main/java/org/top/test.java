package org.top;

import org.top.common.Node;
import org.top.common.TreeNode;

import java.util.*;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {

    }

    public static String validIPAddress(String queryIP) {
        //IPV4
        if (queryIP.indexOf('.') > 0) {
            if (queryIP.charAt(queryIP.length() - 1) == '.') {
                return "Neither";
            }
            String[] split = queryIP.split("\\.");
            if (split.length != 4) {
                return "Neither";
            }
            for (String str : split) {
                // 是否全是数字，并且0<=str<=255, str.length <= 3
                if (!ipv4Valid(str)) {
                    return "Neither";
                }
            }
            return "IPv4";
        } else if (queryIP.indexOf(':') > 0) {
            if (queryIP.charAt(queryIP.length() - 1) == ':') {
                return "Neither";
            }
            String[] split = queryIP.split(":");
            if (split.length != 8) {
                return "Neither";
            }
            for (String str : split) {
                if (!ipv6Valid(str)) {
                    return "Neither";
                }
            }
            return "IPv6";
        } else {
            return "Neither";
        }

    }

    private static boolean ipv6Valid(String str) {
        if (str.length() < 1 || str.length() > 4) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ( !((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) ) {
                return false;
            }
        }

        return true;
    }

    private static boolean ipv4Valid(String str) {
        if (str.length() > 3 || str.length() == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
            // 不能前导0
            if (i == 0 && str.charAt(i) == '0' && str.length() > 1) {
                return false;
            }
            sum = sum * 10 + str.charAt(i) - '0';
        }
        if (sum < 0 || sum > 255) {
            return false;
        }
        return true;
    }
}
