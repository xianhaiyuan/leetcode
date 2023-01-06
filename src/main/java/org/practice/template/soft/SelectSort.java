package org.practice.template.soft;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] params = new int[]{3,2,4,6,2,1};
        selectSort(params);
        Arrays.stream(params).forEach(System.out::println);
    }

    private static void selectSort(int[] params) {
        if (params == null || params.length < 2) {
            return;
        }

        for (int i = 0; i < params.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < params.length; j++) {
                if (params[j] < params[min]) {
                    min = j;
                }
            }
            if (params[min] < params[i]) {
                swap(params, i, min);
            }
        }
    }

    private static void swap(int[] params, int j, int i) {
        int tmp = params[i];
        params[i] = params[j];
        params[j] = tmp;
    }
}
