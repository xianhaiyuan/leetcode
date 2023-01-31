package org.top.堆;

import java.util.PriorityQueue;

import static org.practice.template.soft.QuickSort.swap;

//数组中的第K个最大元素
//https://leetcode-cn.com/problems/kth-largest-element-in-an-array
/*
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

输入: [3,2,1,5,6,4], k = 2
输出: 5

输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4

提示：

    1 <= k <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4

 */
public class kth_largest_element_in_an_array_2 {
    public static void main(String[] args) {
        int[] param = new int[]{1,3,3,4,5,6};
        // partition返回的是下标，如果要求的是第k个最小的元素，则 q == k - 1
        System.out.println(func3(param, 2));

    }
    // 小顶堆解法
    public static int func2(int[] arr, int k) {
        // 小根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        for (int x : arr) {
            if (q.size() < k || q.peek() <= x) q.add(x);
            if (q.size() > k) q.poll();
        }
        return q.peek();
    }

    // 手动建大顶堆解法
    public static int func3(int[] arr, int k) {
        buildMaxHeap(arr, arr.length);
        int heapSize = arr.length;

        // k-1次
        for (int i = arr.length - 1; i > arr.length - k; i--) {
            swap(arr, 0, heapSize - 1);
            heapSize--;
            heapify(arr, 0, heapSize);
        }

        /*int time = k - 1;
        while (time > 0) {
            swap(arr, 0, heapSize - 1);
            heapSize--;
            heapify(arr, 0 ,heapSize);
            time--;
        }*/
        return arr[0];
    }


    public static void buildMaxHeap(int[] arr, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }
    }

    private static void heapify(int[] arr, int i, int heapSize) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;

        if (l < heapSize && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < heapSize && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, heapSize);
        }

    }
}
