package org.top;

import java.util.*;

//https://leetcode.cn/problems/find-median-from-data-stream/
//数据流的中位数
public class find_median_from_data_stream {

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
