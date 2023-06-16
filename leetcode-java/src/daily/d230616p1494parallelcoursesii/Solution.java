package daily.d230616p1494parallelcoursesii;

import java.util.*;

/**
 * 1494. Parallel Courses II
 *
 * https://leetcode.cn/problems/parallel-courses-ii/description/
 *
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n.
 * You are also given an array relations where relations[i] = [prevCoursei, nextCoursei],
 * representing a prerequisite relationship between course prevCoursei and course nextCoursei:
 * course prevCoursei has to be taken before course nextCoursei. Also, you are given the integer k.
 *
 * In one semester, you can take at most k courses as long as you have taken all the prerequisites
 * in the previous semesters for the courses you are taking.
 *
 * Return the minimum number of semesters needed to take all courses. The testcases
 * will be generated such that it is possible to take every course.
 */

public class Solution {

    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] need = new int[1 << n];
        for (int[] edge : relations) {
            need[(1 << (edge[1] - 1))] |= 1 << (edge[0] - 1);
        }

        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE); dp[0] = 0;
        for (int i = 1; i < (1 << n); ++i) {
            need[i] = need[i & (i - 1)] | need[i & (-i)];
            // i 中有任意一门课程的前置课程没有完成学习
            if ((need[i] | i) != i) continue;

            int valid = i ^ need[i]; // 当前学期可以进行学习的课程集合
            if (Integer.bitCount(valid) <= k) { // 如果个数小于 k，则可以全部学习，不再枚举子集
                dp[i] = Math.min(dp[i], dp[i ^ valid] + 1);
            } else { // 否则枚举当前学期需要进行学习的课程集合
                for (int sub = valid; sub > 0; sub = (sub - 1) & valid) {
                    if (Integer.bitCount(sub) <= k) {
                        dp[i] = Math.min(dp[i], dp[i ^ sub] + 1);
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    public static void main(String[] args) {
        assert new Solution().minNumberOfSemesters(4, new int[][]{{2,1},{3,1},{1,4}}, 2) == 3;
        assert new Solution().minNumberOfSemesters(5, new int[][]{{2,1},{3,1},{4,1},{1,5}}, 2) == 4;
        assert new Solution().minNumberOfSemesters(11, new int[][]{}, 2) == 6;
    }

}
