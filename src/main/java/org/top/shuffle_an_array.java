package org.top;

import java.util.Random;

//https://leetcode-cn.com/problems/shuffle-an-array
//打乱数组
public class shuffle_an_array {

    int[] arr;
    int[] origin;

    public shuffle_an_array(int[] nums) {
        this.arr = nums;
        this.origin = new int[nums.length];
        System.arraycopy(nums, 0, origin, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(this.origin, 0, this.arr, 0, this.origin.length);
        return arr;
    }

    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < this.arr.length; i++) {
            int j = i + random.nextInt(this.arr.length - i);
            int tmp = this.arr[i];
            this.arr[i] = this.arr[j];
            this.arr[j] = tmp;
        }
        return this.arr;
    }

}
