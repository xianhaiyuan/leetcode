package org.top;

import java.util.*;

//https://leetcode-cn.com/problems/basic-calculator
//基本计算器
public class basic_calculator {
    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        int sign = 1;
        int res = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            } else if (s.charAt(i) == '+') {
                sign = stack.peek();
            } else if (s.charAt(i) == '-') {
                sign = -stack.peek();
            } else if (s.charAt(i) == '(') {
                stack.push(sign);
            } else if (s.charAt(i) == ')') {
                stack.pop();
            } else {
                int num = 0;
                while (i < s.length() && s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                res += sign * num;
                continue;
            }
            i++;
        }
        return res;
    }

}
