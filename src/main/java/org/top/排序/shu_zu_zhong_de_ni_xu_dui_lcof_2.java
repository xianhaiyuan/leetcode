package org.top.排序;
//https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
//数组中的逆序对
/*
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数

输入: [7,5,6,4]
输出: 5

限制：
0 <= 数组长度 <= 50000
 */
public class shu_zu_zhong_de_ni_xu_dui_lcof_2 {

    public static void main(String[] args) {
        int[] arr = {1,3,2,3,1};
        System.out.println(reversePairs(arr));
    }

    public static int reversePairs(int[] nums) {

        return f(nums, 0, nums.length - 1);

    }

    public static int f(int[] nums, int l, int r) {

        if (l >= r) {
            return 0;
        }

        int ans = 0;

        int mid = l + (r - l) / 2;
        ans += f(nums, l, mid);
        ans += f(nums, mid + 1, r);
        if (nums[mid] > nums[mid + 1]) {
            ans += merge(nums, l, mid, r);
        }
        return ans;

    }

    public static int merge(int[] nums, int l, int mid, int r) {
        int l_tmp = l, mid_tmp = mid + 1;

        int[] arr = new int[r-l+1];
        int index = 0;

        int res = 0;

        while (l_tmp <= mid && mid_tmp <= r) {
            if (nums[l_tmp] <= nums[mid_tmp]) {
                arr[index++] = nums[l_tmp++];
            } else {
                // 由于是递归归并，所以能保证每回的左边相对本身有序，右边相对本身有序，所以左边的nums[l_tmp] > nums[mid_tmp]
                // 那么 l_tmp到mid这些元素是满足 > nums[mid_tmp]，所以把计数算上
                res += mid - l_tmp + 1;
                arr[index++] = nums[mid_tmp++];
            }
        }

        while (l_tmp <= mid) {
            arr[index++] = nums[l_tmp++];
        }

        while (mid_tmp <= r) {
            arr[index++] = nums[mid_tmp++];
        }

        for (int i = 0; i < arr.length; i++) {
            nums[l + i] = arr[i];
        }

        return res;

    }
}
