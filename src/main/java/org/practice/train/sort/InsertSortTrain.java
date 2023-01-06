package org.practice.train.sort;

import java.util.Arrays;

public class InsertSortTrain {
    public static void main(String[] args) {
        int[] params = new int[]{3,2,4,6,2,1};
        insertSort(params);
        Arrays.stream(params).forEach(System.out::println);
    }

    private static void insertSort(int[] params) {

        if (params == null || params.length < 2) {
            return;
        }

        /*for (int i = 0; i < params.length; i++) {
            for (int j = i; j > 0; j--) {
                if (params[j] < params[j - 1]) {
                    swap(params, j, j - 1);
                }
            }
        }*/

        for (int i = 1; i < params.length; i++) {
            int tmp;
            int j;
            for (tmp = params[i], j = i - 1; j >= 0 && tmp < params[j]; j--) {
                params[j + 1] = params[j];
            }
            params[j + 1] = tmp;
        }

    }

    private static void swap(int[] params, int j, int i) {
        int tmp = params[i];
        params[i] = params[j];
        params[j] = tmp;
    }
}
