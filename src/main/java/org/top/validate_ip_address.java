package org.top;

//https://leetcode-cn.com/problems/validate-ip-address
//验证IP地址
public class validate_ip_address {
    public static void main(String[] args) {
        String s = "192.0.0.1";
        String res = validIPAddress(s);
        System.out.println(res);
    }

    public static String validIPAddress(String queryIP) {
        String splitChar = ":";
        if (queryIP.indexOf(".") > 0) {
            splitChar = "\\.";
        }

        if (isIPV4(queryIP, splitChar)) {
            return "IPv4";
        } else if (isIPV6(queryIP, splitChar)) {
            return "IPv6";
        }
        return "Neither";

    }

    public static boolean isNumValid(char num) {
        int n = num - '0';
        if (n >= 0 && n <= 9) {
            return true;
        }
        return false;
    }

    public static boolean isIPV4NumValid(String s) {

        if (s == null || s.length() == 0) {
            return false;
        }

        for (int i = 0 ; i < s.length(); i++) {
            if ((s.length() > 1 && i == 0 && s.charAt(i) == '0') || !isNumValid(s.charAt(i))) {
                return false;
            }
        }

        if (s.length() <= 3 && s.length() >= 0 && (Integer.valueOf(s) >= 0 && Integer.valueOf(s) <= 255)) {
            return true;
        }
        return false;
    }

    public static boolean isIPV4(String str, String splitChar) {

        String[] split = str.split(splitChar);

        int splitCharNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                splitCharNum++;
            }
        }

        if (splitCharNum != 3) {
            return false;
        }

        if (split.length != 4) {
            return false;
        }

        for (String s : split) {
            if (!isIPV4NumValid(s)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isIPV6NumValid(String s) {

        if (s == null || s.length() == 0) {
            return false;
        }

        if (!(s.length() >= 1 && s.length() <= 4)) {
            return false;
        }

        for (int i = 0 ; i < s.length(); i++) {
            if (!((s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) || (s.charAt(i) >= 'a' && s.charAt(i) <= 'f')
                    || (s.charAt(i) >= 'A' && s.charAt(i) <= 'F'))) {
                return false;
            }
        }

        return true;

    }

    public static boolean isIPV6(String str, String splitChar) {

        String[] split = str.split(splitChar);

        int splitCharNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ':') {
                splitCharNum++;
            }
        }

        if (splitCharNum != 7) {
            return false;
        }

        if (split.length != 8) {
            return false;
        }

        for (String s : split) {
            if (!isIPV6NumValid(s)) {
                return false;
            }
        }

        return true;
    }
}
