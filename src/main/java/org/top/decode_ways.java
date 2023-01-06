package org.top;

//https://leetcode-cn.com/problems/decode-ways
//解码方法
public class decode_ways {
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

        int a = (s.charAt(0) - '0') * 10 + s.charAt(1) - '0';

        if (a < 10 || (a > 26 && s.charAt(1) - '0' == 0)) {
            f[1] = 0;
        } else if (a == 10 || a == 20 || (a > 26 && s.charAt(1) - '0' != 0)) {
            f[1] = 1;
        } else {
            f[1] = 2;
        }

        for (int i = 2; i < f.length; i++) {
            a = s.charAt(i) - '0';
            int b = (s.charAt(i - 1) - '0') * 10 + a;
            if (a > 0 && a <= 9) {
                f[i] = f[i-1];
            }
            if (b >= 10 && b <= 26) {
                f[i] += f[i-2];
            }
        }
        return f[f.length - 1];
    }
}
