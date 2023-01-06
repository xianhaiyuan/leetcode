package org.top;
//买卖股票的最佳时机
//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
public class best_time_to_buy_and_sell_stock {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4};
        System.out.println(maxProfit(arr));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }

        return max;
    }
}
