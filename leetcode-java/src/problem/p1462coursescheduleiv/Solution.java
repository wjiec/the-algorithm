package problem.p1462coursescheduleiv;

import common.Checker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 1462. Course Schedule IV
 *
 * https://leetcode.cn/problems/course-schedule-iv/
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
 * take course ai first if you want to take course bi.
 *
 * For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
 * Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a
 * prerequisite of course c, then course a is a prerequisite of course c.
 *
 * You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should
 * answer whether course uj is a prerequisite of course vj or not.
 *
 * Return a boolean array answer, where answer[j] is the answer to the jth query.
 */

public class Solution {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int[] indegree = new int[numCourses];
        boolean[][] requires = new boolean[numCourses][numCourses];
        for (int[] prerequisite : prerequisites) {
            requires[prerequisite[0]][prerequisite[1]] = true;
            indegree[prerequisite[1]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            for (int i = 0; i < numCourses; i++) {
                if (requires[curr][i]) {
                    for (int j = 0; j < numCourses; j++) {
                        if (requires[j][curr]) {
                            requires[j][i] = true;
                        }
                    }
                }

                if (--indegree[i] == 0) queue.add(i);
            }
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            ans.add(requires[query[0]][query[1]]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().checkIfPrerequisite(2, new int[][]{
            {1,0}
        }, new int[][]{{0,1},{1,0}}), List.of(false,true));

        assert Checker.check(new Solution().checkIfPrerequisite(2, new int[][]{
        }, new int[][]{{1,0},{0,1}}), List.of(false,false));

        assert Checker.check(new Solution().checkIfPrerequisite(3, new int[][]{
            {1,2},{1,0},{2,0}
        }, new int[][]{{1,0},{1,2}}), List.of(true,true));
    }

}
