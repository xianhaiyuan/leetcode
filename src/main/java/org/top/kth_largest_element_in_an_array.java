package org.top;

import java.util.PriorityQueue;

import static org.practice.template.soft.QuickSort.swap;
//数组中的第K个最大元素
//https://leetcode-cn.com/problems/kth-largest-element-in-an-array
public class kth_largest_element_in_an_array {
    public static void main(String[] args) {
        int[] param = new int[]{1,3,3,4,5,6};
        // partition返回的是下标，如果要求的是第k个最小的元素，则 q == k - 1
        System.out.println(func3(param, 2));

    }

    public static int findKthLargest2(int[] nums, int k) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int pos = partition2(nums, l, r);
            if (nums.length - k == pos) {
                return nums[pos];
            } else if (pos < nums.length - k) {
                l = pos + 1;
            } else {
                r = pos - 1;
            }
        }

        return nums[0];
    }

    public static int partition2(int[] nums, int left, int right) {
        int left_tmp = left - 1;
        int right_tmp = right;
        int index = left;

        while (index < right_tmp) {
            if (nums[index] < nums[right]) {
                swap(nums, index++, ++left_tmp);
            } else if (nums[index] > nums[right]) {
                swap(nums, index, --right_tmp);
            } else {
                index++;
            }
        }
        swap(nums, right, right_tmp);
        return left_tmp + 1;
    }

    public static int func(int[] arr, int k) {
        int res = quickSelect(arr, 0, arr.length - 1, k);
        return res;
    }

    public static int quickSelect(int[] arr, int l, int r, int k) {
        int q = partition(arr, l, r);
        if (q == k) {
            return arr[q];
        } else {
            return q < k ? quickSelect(arr, q + 1, r, k) : quickSelect(arr, l, q - 1, k);
        }
    }

    private static int partition(int[] arr, int l, int r) {
        int x = arr[r];
        int left_tmp = l - 1;
        int right_tmp = r;
        int index = l;
        while (index < right_tmp) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++left_tmp);
            } else {
                swap(arr, index, --right_tmp);
            }
        }
        swap(arr, r, right_tmp);
        return left_tmp + 1;
    }

    public static int partition1(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        // 只排小的，大的不动
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
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

        for (int i = arr.length - 1; i > arr.length - k; i--) {
            swap(arr, 0, arr.length - 1);
            heapSize--;
            heapify(arr, 0, heapSize);
        }
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
