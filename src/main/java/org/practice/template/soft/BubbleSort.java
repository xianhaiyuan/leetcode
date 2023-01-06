package org.practice.template.soft;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] params = new int[]{3,2,4,6,2,1};
        bubbleSort(params);
        Arrays.stream(params).forEach(System.out::println);
    }

    private static void bubbleSort(int[] params) {
        if (params == null || params.length < 2) {
            return;
        }
        for (int i = 0; i < params.length - 1; i++) {
            for (int j = 0; j < params.length - 1 - i; j++) {
                if (params[j] > params[j + 1]) {
                    swap(params, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] params, int j, int i) {
        int tmp = params[i];
        params[i] = params[j];
        params[j] = tmp;
    }
}
