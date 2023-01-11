package org.top;
//https://leetcode-cn.com/problems/edit-distance
//编辑距离
public class edit_distance {
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
                    f[i][j] = Math.min(f[i-1][j-1], Math.min(f[i-1][j], f[i][j-1])) + 1;
                }
            }
        }

        return f[m][n];
    }
}
