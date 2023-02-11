package org.top.排序;
import java.util.*;
//https://www.nowcoder.com/practice/3245215fffb84b7b81285493eae92ff0?tpId=37&tqId=21226&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D3%26tpId%3D37%26type%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=3
/*
描述

明明生成了NN个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
数据范围： 1≤n≤1000 1≤n≤1000  ，输入的数字大小满足 1≤val≤500 1≤val≤500
输入描述：
第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
输出描述：

输出多行，表示输入数据处理后的结果

输入：

3
2
2
1

输出：

1
2

说明：

输入解释：
第一个数字是3，也即这个小样例的N=3，说明用计算机生成了3个1到500之间的随机整数，接下来每行一个随机数字，共3行，也即这3个随机数字为：
2
2
1
所以样例的输出为：
1
2
 */
public class counting_sort_4 {

    // 计数排序
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] res = new int[1000];
        // in.hasNextInt() 输入\后终止输入
        while (in.hasNextInt()) {
            int num = in.nextInt();
            res[num]++;
            size--;
        }
        for (int i = 0; i < 1000; i++) {
            if (res[i] > 0) {
                System.out.println(i);
            }
        }
    }
}
