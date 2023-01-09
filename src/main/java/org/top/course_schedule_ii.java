package org.top;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.cn/problems/course-schedule-ii
//课程表 II
public class course_schedule_ii {

    public static void main(String[] args) {
        int[][] arr = new int[][] { {1,0} };
        int[] res = findOrder(2, arr);
        System.out.println(res.length);
    }

    public static boolean valid = true;

    public static int j;

    // 拓扑排序，有向无环图
    // 深度优先
    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        int[] vistited = new int[numCourses];
        int[] ans = new int[numCourses];
        List<List<Integer>> edges = new ArrayList<>();

        j = numCourses - 1;

        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i] == null || prerequisites[i].length == 0) {
                continue;
            }
            List<Integer> list = edges.get(prerequisites[i][1]);
            list.add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (vistited[i] == 0) {
                dfs(i, vistited, edges, ans);
            }
        }

        if (!valid) {
            return new int[0];
        }

        return ans;
    }

    private static void dfs(int index, int[] visited, List<List<Integer>> edges, int[] ans) {

        List<Integer> points = edges.get(index);

        visited[index] = 1;
        for (Integer p : points) {
            // 有环
            if (visited[p] == 1) {
                valid = false;
                return;
            }

            // 剪枝
            if (valid && visited[p] == 0) {
                dfs(p, visited, edges, ans);
            }

        }

        // 表示从该点往后的递归已经走过
        visited[index] = 2;

        ans[j--] = index;

    }

    public static int[] findOrder1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        // 入度
        int[] inEdge = new int[numCourses];

        int[] ans = new int[numCourses];

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
        int j = 0;
        while (!queue.isEmpty()) {

            Integer point = queue.poll();
            ans[j++] = point;
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

        if (visited != numCourses) {
            return new int[0];
        }

        return ans;

    }

}
