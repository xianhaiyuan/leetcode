package org.top;
//https://leetcode.cn/problems/stone-game/
//石子游戏
public class stone_game {
    public boolean stoneGame(int[] piles) {
        // f[i][j] 代表 i 到 j 当前玩家与另一个玩家的石子数量之差的最大值
        int[][] f = new int[piles.length][piles.length];
        for (int i = 0; i < piles.length; i++) {
            f[i][i] = piles[i];
        }

        for (int i = 0; i < piles.length - 1; i++) {
            for (int j = i+1; j < piles.length; j++) {
                f[i][j] = Math.max(piles[i] - f[i+1][j], piles[j] - f[i][j-1]);
            }
        }
        // 因为只需找到最优质，所以先手从哪里开始都可以
        return f[0][piles.length - 1] > 0;
    }

    public boolean stoneGame1(int[] piles) {
        return true;
    }
}
