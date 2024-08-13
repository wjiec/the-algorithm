package daily.d230728p2050parallelcoursesiii;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 2050. Parallel Courses III
 *
 * https://leetcode.cn/problems/parallel-courses-iii/
 *
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n.
 * You are also given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej]
 * denotes that course prevCoursej has to be completed before course nextCoursej (prerequisite relationship).
 *
 * Furthermore, you are given a 0-indexed integer array time where time[i] denotes how
 * many months it takes to complete the (i+1)th course.
 *
 * You must find the minimum number of months needed to complete all the courses following these rules:
 *
 * You may start taking a course at any time if the prerequisites are met.
 * Any number of courses can be taken at the same time.
 * Return the minimum number of months needed to complete all the courses.
 *
 * Note: The test cases are generated such that it is possible to complete every
 * course (i.e., the graph is a directed acyclic graph).
 * @noinspection unchecked
 */

public class Solution {

    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] inDegree = new int[n + 1];
        Set<Integer>[] map = new Set[n + 1];
        for (int i = 0; i <= n; i++) map[i] = new HashSet<>();
        for (var relation : relations) {
            inDegree[relation[1]]++;
            map[relation[0]].add(relation[1]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < map.length; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        int ans = 0;
        int[] dp = new int[n + 1];
        for (int i = 0; i < time.length; i++) {
            ans = Math.max(ans, dp[i + 1] = time[i]);
        }
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            for (var next : map[curr]) {
                dp[next] = Math.max(dp[next], dp[curr] + time[next - 1]);
                ans = Math.max(ans, dp[next]);
                if (--inDegree[next] == 0) queue.add(next);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumTime(3, new int[][]{{1,3},{2,3}}, new int[]{3,2,5}) == 8;
        assert new Solution().minimumTime(5, new int[][]{{1,5},{2,5},{3,5},{3,4},{4,5}}, new int[]{1,2,3,4,5}) == 12;
    }

}
