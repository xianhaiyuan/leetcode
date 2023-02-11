package org.top.数学相关;
import java.util.*;
//https://www.nowcoder.com/practice/4b1658fd8ffb4217bc3b7e85a38cfaf2?tpId=37&tqId=21309&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D86%26tpId%3D37%26type%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=86
/*
描述

求一个int类型数字对应的二进制数字中1的最大连续数，例如3的二进制为00000011，最大连续2个1
数据范围：数据组数：1≤t≤5 1≤t≤5 ，1≤n≤500000 1≤n≤500000
进阶：时间复杂度：O(logn) O(logn) ，空间复杂度：O(1) O(1)
输入描述：

输入一个int类型数字
输出描述：

输出转成二进制之后连续1的个数

输入：
200
输出：
2
说明：
200的二进制表示是11001000，最多有2个连续的1。
 */
public class max_continuous_bit_count {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int num = in.nextInt();
            int count = 0;
            int max_count = 0;
            while (num > 0) {
                if (num%2 == 1) {
                    count++;
                } else {
                    max_count = Math.max(max_count, count);
                    count = 0;
                }
                num /= 2;
            }
            max_count = Math.max(max_count, count);
            System.out.println(max_count);
        }
    }

}
