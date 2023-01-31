package org.top.堆;

import java.util.*;

//https://leetcode.cn/problems/find-median-from-data-stream/
//数据流的中位数
/*
中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
    例如 arr = [2,3,4] 的中位数是 3 。
    例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
实现 MedianFinder 类:
    MedianFinder() 初始化 MedianFinder 对象。
    void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
    double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。

输入
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
输出
[null, null, null, 1.5, null, 2.0]

解释
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

提示:

    -10^5 <= num <= 10^5
    在调用 findMedian 之前，数据结构中至少有一个元素
    最多 5 * 10^4 次调用 addNum 和 findMedian


 */
public class find_median_from_data_stream_1 {

    public static void main(String[] args) {

    }

    // 大顶堆
    static PriorityQueue<Integer> queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
    // 小顶堆
    static PriorityQueue<Integer> queMax = new PriorityQueue<Integer>((a, b) -> (a - b));


    public void addNum(int num) {
        // 如果当前数字小于大顶堆的peek，则存进去
        if (queMin.isEmpty() || queMin.peek() >= num) {
            queMin.offer(num);
            if (queMin.size() > queMax.size() + 1) {
                queMax.offer(queMin.poll());
            }
        } else {
            queMax.offer(num);
            if (queMax.size() > queMin.size()) {
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        // queMin 长度大于或等于 queMax
        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        }
        return (queMin.peek() + queMax.peek()) / 2.0;
    }

}
