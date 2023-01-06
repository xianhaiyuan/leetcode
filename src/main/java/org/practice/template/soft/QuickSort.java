package org.practice.template.soft;

import java.lang.*;
import java.util.*;

public class QuickSort {
    public static void main(String[] args) {
        int[] params = new int[]{3,2,4,6,2,1};
        quickSort(params);
        Arrays.stream(params).forEach(System.out::println);
    }

    public static void quickSort(int[] args) {
        if (args == null || args.length < 2) {
            return;
        }
        process(args, 0, args.length - 1);
    }

    public static void process(int[] args, int left, int right) {
        if (left >= right) {
            return;
        }

        swap(args, left + (int) (Math.random() * (right - left + 1)), right);
        int[] pos = partition(args, left, right);
        process(args, left, pos[0] - 1);
        process(args, pos[1] + 1, right);
    }

    private static int[] partition(int[] args, int left, int right) {

        if (left > right) {
            return new int[] {-1, args.length};
        }
        if (left == right) {
            return new int[] {left, right};
        }

        int left_tmp = left - 1;
        int right_tmp = right;
        int index = left;
        while (index < right_tmp) {
            if (args[index] == args[right]) {
                index++;
            } else if (args[index] < args[right]) {
                swap(args, ++left_tmp, index++);
            } else {
                swap(args, --right_tmp, index);
            }
        }

        swap(args, right_tmp, right);

        return new int[]{++left_tmp, right_tmp};

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
