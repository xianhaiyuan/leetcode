package org.top.数字与字符串;

import java.util.*;

//https://leetcode-cn.com/problems/basic-calculator
//基本计算器
/*
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。

输入：s = "1 + 1"
输出：2

输入：s = " 2-1 + 2 "
输出：3

输入：s = "(1+(4+5+2)-3)+(6+8)"
输出：23

提示：

    1 <= s.length <= 3 * 10^5
    s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
    s 表示一个有效的表达式
    '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
    '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
    输入中不存在两个连续的操作符
    每个数字和运行的计算将适合于一个有符号的 32位 整数

 */
public class basic_calculator_2 {
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
