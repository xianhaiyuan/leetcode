package org.top.深度优先和广度优先;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.cn/problems/number-of-provinces/
//省份数量
/*
有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
返回矩阵中 省份 的数量。

输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2

输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
输出：3

提示：

    1 <= n <= 200
    n == isConnected.length
    n == isConnected[i].length
    isConnected[i][j] 为 1 或 0
    isConnected[i][i] == 1
    isConnected[i][j] == isConnected[j][i]

 */
public class number_of_provinces_6 {
    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{{0,0,0},{0,0,0},{0,0,0}}));
    }
    // 深度优先
    public static int findCircleNum(int[][] isConnected) {
        int cityNum = isConnected.length;
        boolean[] visited = new boolean[cityNum];
        int provinces = 0;
        for (int i = 0; i < cityNum; i++) {
            if (!visited[i]) {
                dfs(visited, isConnected, cityNum, i);
                provinces++;
            }
        }
        return provinces;
    }

    private static void dfs(boolean[] visited, int[][] isConnected, int cityNum, int i) {
        for (int j = 0; j < cityNum; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(visited, isConnected, cityNum, j);
            }
        }
    }


    // 广度优先
    public int findCircleNum1(int[][] isConnected) {
        int cityNum = isConnected.length;
        boolean[] visited = new boolean[cityNum];
        int provinces = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < cityNum; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;

                    for (int k = 0; k < cityNum; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }

                }
                provinces++;
            }
        }
        return provinces;
    }

    // 并查集
    // 初始时，每个城市都属于不同的连通分量。遍历矩阵 isConnected,如果两个城市之间有相连关系，则它们属于同一个连通分量，对它们进行合并
    // 遍历矩阵 isConnected的全部元素之后，计算连通分量的总数，即为省份的总数。
    public int findCircleNum2(int[][] isConnected) {
        int cityNum = isConnected.length;
        int provinces = 0;
        int[] parent = new int[cityNum];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < cityNum; i++) {
            for (int j = i + 1; j < cityNum; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                provinces++;
            }
        }

        return provinces;

    }

    public void union(int[] parent, int i, int j) {

        parent[findParent(parent, i)] = findParent(parent, j);

    }

    public int findParent(int[] parent, int index) {
        if (parent[index] == index) {
            return index;
        }
        return findParent(parent, parent[index]);
    }


}
