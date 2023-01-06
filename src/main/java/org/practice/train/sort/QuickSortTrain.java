package org.practice.train.sort;
import java.util.Arrays;


public class QuickSortTrain {
    public static void main(String[] args) {
        int[] params = new int[]{3,2,4,6,2,1};
        quickSort(params);
        Arrays.stream(params).forEach(System.out::println);
    }

    private static void quickSort(int[] params) {
        if (params == null || params.length < 2) {
            return;
        }
        process(params, 0, params.length - 1);

    }

    private static void process(int[] params, int left, int right) {
        if (left >= right) {
            return;
        }

        swap(params, right, left + (int) (Math.random() * (right - left + 1) / 2));

        int[] pos = partition(params, left, right);
        process(params, left, pos[0] - 1);
        process(params, pos[1] + 1, right);
    }

    private static int[] partition(int[] params, int left, int right) {
        if (left > right) {
            return new int[] {-1, params.length};
        }
        if (left == right) {
            return new int[] {left ,right};
        }

        int left_tmp = left - 1;
        int right_tmp = right;
        int index = left;

        while (index < right_tmp) {
            if (params[index] == params[right_tmp]) {
                index++;
            } else if (params[index] < params[right_tmp]) {
                swap(params, index++, ++left_tmp);
            } else {
                swap(params, index, --right_tmp);
            }
        }

        swap(params, right, right_tmp);

        return new int[] {left_tmp + 1, right_tmp};

    }


    public static void swap(int[] args, int start, int end) {
        if (start == end) {
            return;
        }
        int tmp;
        tmp = args[start];
        args[start] = args[end];
        args[end] = tmp;
    }


}
