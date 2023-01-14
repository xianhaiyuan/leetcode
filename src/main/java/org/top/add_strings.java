package org.top;
//https://leetcode.cn/problems/add-strings/
//字符串相加
public class add_strings {
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
