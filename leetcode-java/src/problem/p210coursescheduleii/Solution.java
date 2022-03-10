package problem.p210coursescheduleii;

import common.Checker;

import java.util.*;

/**
 * 210. Course Schedule II
 *
 * https://leetcode-cn.com/problems/course-schedule-ii/
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers,
 * return any of them.
 *
 * If it is impossible to finish all courses, return an empty array.
 */

public class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] conditions = new int[numCourses];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (var prerequisite : prerequisites) {
            if (!map.containsKey(prerequisite[1])) {
                map.put(prerequisite[1], new HashSet<>());
            }
            map.get(prerequisite[1]).add(prerequisite[0]);
            // 完成prerequisite[0]有几个前置条件
            conditions[prerequisite[0]]++;
        }

        int idx = 0;
        int[] ans = new int[numCourses];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (conditions[i] == 0) {
                queue.add(i);
                ans[idx++] = i;
            }
        }

        int learned = 0;
        while (!queue.isEmpty()) {
            learned++;
            int curr = queue.remove();
            if (map.containsKey(curr)) {
                for (int next : map.get(curr)) {
                    if (--conditions[next] == 0) {
                        queue.add(next);
                        ans[idx++] = next;
                    }
                }
            }
        }

        return learned == numCourses ? ans : new int[]{};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findOrder(2,
            new int[][]{{1, 0}}), new int[]{0,1});

        assert Checker.check(new Solution().findOrder(4,
            new int[][]{{1, 0}, {2, 0}, {3, 1}, {2, 1}}), new int[]{0,1,2,3});

        assert Checker.check(new Solution().findOrder(1,
            new int[][]{}), new int[]{0});
    }

}
