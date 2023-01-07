package org.top;
//https://leetcode-cn.com/problems/sqrtx
//x 的平方根
public class sqrtx {
    public static void main(String[] args) {
        System.out.println(mySqrt(0));
    }

    public static int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long)mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
