package org.top;

import org.top.二分查找.find_in_mountain_array_2;

import java.util.PriorityQueue;

//https://github.com/afatcoder/LeetcodeTop/blob/master/microsoft/SDE.md
public class test {
    public static void main(String[] args) {

    }

    static PriorityQueue<Integer> queMin = new PriorityQueue<>((a,b)->a-b);
    static PriorityQueue<Integer> queMax = new PriorityQueue<>((a,b)->b-a);

    public void addNum(int num) {
        if (queMax.isEmpty() || queMax.peek() < num) {
            queMax.offer(num);
            if (queMax.size() > queMin.size() + 1) {
                queMin.offer(queMax.poll());
            }
        } else {
            queMin.offer(num);
            if (queMin.size() > queMax.size()) {
                queMax.offer(queMin.poll());
            }
        }

    }

    public double findMedian() {
        if (queMin.size() == queMax.size()) {
            return (queMin.peek() + queMax.peek()) / 2.0;
        } else {
            return queMax.peek();
        }
    }


}
