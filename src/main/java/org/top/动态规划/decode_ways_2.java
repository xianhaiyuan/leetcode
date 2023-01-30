package org.top.动态规划;

//https://leetcode-cn.com/problems/decode-ways
//解码方法
/*
一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
    "AAJF" ，将消息分组为 (1 1 10 6)
    "KJF" ，将消息分组为 (11 10 6)
注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
题目数据保证答案肯定是一个 32 位 的整数。

输入：s = "12"
输出：2
解释：它可以解码为 "AB"（1 2）或者 "L"（12）。

输入：s = "226"
输出：3
解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。

输入：s = "06"
输出：0
解释："06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。

提示：

    1 <= s.length <= 100
    s 只包含数字，并且可能包含前导零。

 */
public class decode_ways_2 {
    public static void main(String[] args) {
        String s = "30";
        int i = numDecodings(s);
        System.out.println(i);

    }

    public static int numDecodings(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return s.charAt(0) - '0' != 0 ? 1 : 0;
        }

        int[] f = new int[s.length()];
        f[0] = s.charAt(0) - '0' != 0 ? 1 : 0;

        int num = (s.charAt(0) - '0') * 10 + s.charAt(1) - '0';

        if (num < 10 || (num > 26 && s.charAt(1) - '0' == 0)) {
            f[1] = 0;
        } else if (num == 10 || num == 20 || (num > 26 && s.charAt(1) - '0' != 0)) {
            f[1] = 1;
        } else {
            f[1] = 2;
        }

        for (int i = 2; i < f.length; i++) {
            num = s.charAt(i) - '0';
            int cur = (s.charAt(i - 1) - '0') * 10 + num;
            if (num > 0 && num <= 9) {
                f[i] = f[i-1];
            }
            if (cur >= 10 && cur <= 26) {
                f[i] += f[i-2];
            }
        }
        return f[f.length - 1];
    }
}
