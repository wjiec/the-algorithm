package problem.p207courseschedule;

import java.util.*;

/**
 * 207. Course Schedule
 *
 * https://leetcode-cn.com/problems/course-schedule/
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 */

public class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] count = new int[numCourses];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (var prerequisite : prerequisites) {
            if (!map.containsKey(prerequisite[1])) {
                map.put(prerequisite[1], new HashSet<>());
            }
            map.get(prerequisite[1]).add(prerequisite[0]);
            count[prerequisite[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (count[i] == 0) queue.add(i);
        }

        int learned = 0;
        while (!queue.isEmpty()) {
            learned++;
            int curr = queue.remove();
            if (map.containsKey(curr)) {
                for (var next : map.get(curr)) {
                    if (--count[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }

        return learned == numCourses;
    }

    public static void main(String[] args) {
        assert new Solution().canFinish(2, new int[][]{{1, 0}});
        assert !new Solution().canFinish(2, new int[][]{{1, 0}, {0, 1}});
    }

}
