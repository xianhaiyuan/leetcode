package org.top.动态规划;
//https://leetcode.cn/problems/stone-game/
//石子游戏
/*
Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i] 。
游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。
假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false 。

输入：piles = [5,3,4,5]
输出：true
解释：
Alice 先开始，只能拿前 5 颗或后 5 颗石子 。
假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
如果 Bob 拿走前 3 颗，那么剩下的是 [4,5]，Alice 拿走后 5 颗赢得 10 分。
如果 Bob 拿走后 5 颗，那么剩下的是 [3,4]，Alice 拿走后 4 颗赢得 9 分。
这表明，取前 5 颗石子对 Alice 来说是一个胜利的举动，所以返回 true 。

输入：piles = [3,7,2,3]
输出：true

提示：

    2 <= piles.length <= 500
    piles.length 是 偶数
    1 <= piles[i] <= 500
    sum(piles[i]) 是 奇数

 */
public class stone_game_8 {
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
