package org.top;
//https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
//把数字翻译成字符串
public class ba_shu_zi_fan_yi_cheng_zi_fu_chuan_lcof {

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
