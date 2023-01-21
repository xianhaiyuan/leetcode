package org.top;

public class string_to_integer_atoi {

    public static void main(String[] args) {
        System.out.println(myAtoi(" "));
    }

    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int index = 0;
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        if (index == s.length()) {
            return 0;
        }
        int sign = 1;
        if (s.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (s.charAt(index) == '+') {
            index++;
        }



        int res = 0;

        while (index < s.length()) {
            char c = s.charAt(index);
            if (c < '0' || c > '9') {
                break;
            }

            // 判断是否溢出
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && c - '0' > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }

            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && c - '0' > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            res = res * 10 + sign * (c - '0');
            index++;
        }

        return res;
    }
}
