package org.top;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.cn/problems/number-of-provinces/
//省份数量
public class number_of_provinces {
    public static void main(String[] args) {

    }
    // 深度优先
    public int findCircleNum(int[][] isConnected) {
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

    private void dfs(boolean[] visited, int[][] isConnected, int cityNum, int i) {
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
