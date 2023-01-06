package org.practice.template.soft;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] params = new int[]{3,2,4,6,2,1};
        insertSort(params);
        Arrays.stream(params).forEach(System.out::println);
    }

    private static void insertSort(int[] params) {
        if (params == null || params.length < 2) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            for (int j = i; j > 0 && params[j] < params[j-1]; j--) {
                swap(params, j, j - 1);
            }
        }
    }

    private static void swap(int[] params, int j, int i) {
        int tmp = params[i];
        params[i] = params[j];
        params[j] = tmp;
    }
}
