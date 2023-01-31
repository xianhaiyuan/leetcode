package org.top.动态规划;
//https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
//把数字翻译成字符串
/*
给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"

0 <= num < 2^31
 */
public class ba_shu_zi_fan_yi_cheng_zi_fu_chuan_lcof_1 {

    public static void main(String[] args) {
        int res = translateNum(1);
        System.out.println(res);
    }

    public static int translateNum(int num) {

        if (num / 10 == 0) {
            return 1;
        }

        int n = 0;
        int num1 = num;
        while (num1 > 0) {
            num1 /= 10;
            n++;
        }
        char[] arr = new char[n];
        num1 = num;
        int n1 = n - 1;
        while (num1 > 0) {
            int k = num1 % 10;
            arr[n1--] = (char) (k + '0');
            num1 /= 10;
        }
        int[] f = new int[n];
        int sum = 0;

        f[0] = 1;

        sum += arr[0] - '0';

        if (arr[0] == '0') {
            f[1] = 1;
        }

        sum = sum * 10 + arr[1] - '0';
        if (sum >= 10 && sum <= 25) {
            f[1] = 2;
        } else {
            f[1] = 1;
        }


        for (int i = 2; i < arr.length; i++) {
            f[i] = f[i-1];
            sum = (arr[i-1] - '0') * 10 + (arr[i] - '0');
            if (sum >= 10 && sum <= 25) {
                f[i] += f[i-2];
            }
        }

        return f[n-1];

    }
}
