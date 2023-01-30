package org.top.数字与字符串;
//https://leetcode.cn/problems/add-strings/
//字符串相加
/*
给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。

输入：num1 = "11", num2 = "123"
输出："134"

输入：num1 = "456", num2 = "77"
输出："533"

输入：num1 = "0", num2 = "0"
输出："0"

1 <= num1.length, num2.length <= 104
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零
 */
public class add_strings_1 {
    public static void main(String[] args) {
        String num1 = "456", num2 = "77";
        System.out.println(addStrings(num1, num2));
    }
    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        int add = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int tmp = (i >= 0 ? num1.charAt(i) - '0' : 0) + (j >= 0 ? num2.charAt(j) - '0' : 0);

            int s = tmp + add;

            add = s / 10;

            sb.append(s % 10);

            i--;
            j--;

        }

        if (add > 0) {
            sb.append(add);
        }

        return sb.reverse().toString();
    }
}
