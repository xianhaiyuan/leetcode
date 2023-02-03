package org.top.数字处理;
//https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum
//将数组分成和相等的三个部分
/*
给你一个整数数组 arr，只有可以将其划分为三个和相等的 非空 部分时才返回 true，否则返回 false。
形式上，如果可以找出索引 i + 1 < j 且满足 (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1]) 就可以将数组三等分。

输入：arr = [0,2,1,-6,6,-7,9,1,2,0,1]
输出：true
解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1

输入：arr = [0,2,1,-6,6,7,9,-1,2,0,1]
输出：false

输入：arr = [3,3,6,5,-2,2,5,1,-9,4]
输出：true
解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4

提示：

    3 <= arr.length <= 5 * 10^4
    -10^4 <= arr[i] <= 10^4

 */
public class partition_array_into_three_parts_with_equal_sum_13 {

    public static void main(String[] args) {
        int[] arr = new int[]{14,6,-10,2,18,-7,-4,11};
        System.out.println(canThreePartsEqualSum(arr));
    }

    public static boolean canThreePartsEqualSum(int[] arr) {
        int left_sum = 0, right_sum = 0;
        int left_index = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int target = sum / 3;
        for (int i = 0; i < arr.length - 2; i++) {
            left_sum += arr[i];
            if (left_sum == target) {
                left_index = i;
                break;
            }
        }
        for (int i = arr.length - 1; i > left_index + 1; i--) {
            right_sum += arr[i];
            if (right_sum == target && sum - left_sum - right_sum == left_sum) {
                return true;
            }
        }
        return false;
    }

    public boolean canThreePartsEqualSum1(int[] arr) {
        int l, r;
        int left_sum = 0, right_sum;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        for (int i = 0; i < arr.length - 2; i++) {
            l = i;
            r = arr.length - 1;
            right_sum = 0;
            left_sum += arr[i];
            while (l + 1 < r) {
                right_sum += arr[r];

                if (left_sum == right_sum && left_sum == sum - left_sum - right_sum) {
                    return true;
                }

                r--;
            }
        }

        return false;
    }

}
