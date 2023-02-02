package org.top.字符处理;

//https://leetcode-cn.com/problems/validate-ip-address
//验证IP地址
/*
给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
    1 <= xi.length <= 4
    xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
    在 xi 中允许前导零。
例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。

输入：queryIP = "172.16.254.1"
输出："IPv4"
解释：有效的 IPv4 地址，返回 "IPv4"

输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
输出："IPv6"
解释：有效的 IPv6 地址，返回 "IPv6"

输入：queryIP = "256.256.256.256"
输出："Neither"
解释：既不是 IPv4 地址，又不是 IPv6 地址

提示：
    queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。

 */
public class validate_ip_address_2 {
    public static void main(String[] args) {
        String s = "192.0.0.1";
        String res = validIPAddress(s);
        System.out.println(res);
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
