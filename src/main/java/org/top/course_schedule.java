package org.top;

import java.util.*;

//https://leetcode-cn.com/problems/course-schedule
//课程表
public class course_schedule {

    public static void main(String[] args) {
        int[][] arr = new int[][] { {1,4}, {2,4},{3,1},{3,2} };
        System.out.println(canFinish1(5, arr));
    }

    public static boolean ans = true;

    public static boolean valid = true;

    // 拓扑排序，有向无环图
    // 深度优先
    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        int[] vistited = new int[numCourses];
        List<List<Integer>> edges = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = edges.get(prerequisites[i][1]);
            list.add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (vistited[i] == 0) {
                dfs(i, vistited, edges);
            }
        }
        return ans;
    }

    private static void dfs(int index, int[] visited, List<List<Integer>> edges) {

        List<Integer> points = edges.get(index);

        if (points == null) {
            return;
        }
        visited[index] = 1;

        for (Integer p : points) {

            // 有环
            if (visited[p] == 1) {
                ans = false;
                valid = false;
                return;
            }

            // 剪枝
            if (valid && visited[p] == 0) {
                dfs(p, visited, edges);
            }

        }
        
        // 表示从该点往后的递归已经走过
        visited[index] = 2;

    }



    // 广度优先
    public static boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        // 入度
        int[] inEdge = new int[numCourses];

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = edges.get(prerequisites[i][1]);
            list.add(prerequisites[i][0]);
            inEdge[prerequisites[i][0]]++;
        }

        for (int i = 0; i < inEdge.length; i++) {
            if (inEdge[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {

            Integer point = queue.poll();
            visited++;

            List<Integer> list = edges.get(point);
            for (Integer item : list) {
                inEdge[item]--;
                // 入度为0才将节点放入队列，这样有环才能判断出来
                if (inEdge[item] == 0) {
                    queue.offer(item);
                }
            }

        }

        return visited == numCourses;

    }

}
