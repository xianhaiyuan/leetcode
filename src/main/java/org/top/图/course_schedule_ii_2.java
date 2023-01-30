package org.top.图;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.cn/problems/course-schedule-ii
//课程表 II
/*
现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。

输入：numCourses = 2, prerequisites = [[1,0]]
输出：[0,1]
解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。

输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
输出：[0,2,1,3]
解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。

输入：numCourses = 1, prerequisites = []
输出：[0]
 */
public class course_schedule_ii_2 {

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

        // 回溯，到底才直到能否成功，成功后再倒序添加元素
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
