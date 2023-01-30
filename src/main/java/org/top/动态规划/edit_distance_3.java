package org.top.动态规划;
//https://leetcode-cn.com/problems/edit-distance
//编辑距离
/*
给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
你可以对一个单词进行如下三种操作：
    插入一个字符
    删除一个字符
    替换一个字符

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')

提示：

    0 <= word1.length, word2.length <= 500
    word1 和 word2 由小写英文字母组成


 */
public class edit_distance_3 {
    public static void main(String[] args) {
        String w1 = "sea";
        String w2 = "eat";
        System.out.println(minDistance(w1, w2));
    }

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            f[0][i] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            f[j][0] = j;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    f[i][j] = f[i-1][j-1];
                } else {
                    // f[i-1][j-1] 换，f[i-1][j] 增，f[i][j-1] 删
                    f[i][j] = Math.min(f[i-1][j-1], Math.min(f[i-1][j], f[i][j-1])) + 1;
                }
            }
        }

        return f[m][n];
    }
}
