package org.practice.train.sort;

import java.util.Arrays;

public class MergeSortTrain {
    public static void main(String[] args) {
        int[] params = new int[]{3,2,4,6,2,1};
        mergeSort(params);
        Arrays.stream(params).forEach(System.out::println);
    }

    private static void mergeSort(int[] params) {

        if (params == null || params.length < 2) {
            return;
        }

        process(params, 0, params.length - 1);

    }

    private static void process(int[] params, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        process(params, left, mid);
        process(params, mid + 1, right);
        if (params[mid] > params[mid + 1]) {
            merge(params, left, mid, right);
        }

    }

    private static void merge(int[] params, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int left_tmp = left;
        int mid_tmp = mid + 1;
        int index = 0;
        while (left_tmp <= mid && mid_tmp <= right) {
            tmp[index++] = params[left_tmp] < params[mid_tmp] ? params[left_tmp++] : params[mid_tmp++];
        }
        while (left_tmp <= mid) {
            tmp[index++] = params[left_tmp++];
        }
        while (mid_tmp <= right) {
            tmp[index++] = params[mid_tmp++];
        }

        for (int i = 0; i < tmp.length; i++) {
            params[i + left] = tmp[i];
        }
    }


}
