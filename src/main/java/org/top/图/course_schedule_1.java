package org.top.图;

import java.util.*;

//https://leetcode-cn.com/problems/course-schedule
//课程表
/*
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。

提示：

    1 <= numCourses <= 10^5
    0 <= prerequisites.length <= 5000
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    prerequisites[i] 中的所有课程对 互不相同

 */
public class course_schedule_1 {

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
