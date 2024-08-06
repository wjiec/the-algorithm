package weekly.w409.B;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 3243. Shortest Distance After Road Addition Queries I
 *
 * https://leetcode.cn/contest/weekly-contest-409/problems/shortest-distance-after-road-addition-queries-i/
 *
 * You are given an integer n and a 2D integer array queries.
 *
 * There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road
 * from city i to city i + 1 for all 0 <= i < n - 1.
 *
 * queries[i] = [ui, vi] represents the addition of a new unidirectional road from
 * city ui to city vi. After each query, you need to find the length of the shortest
 * path from city 0 to city n - 1.
 *
 * Return an array answer where for each i in the range [0, queries.length - 1],
 * answer[i] is the length of the shortest path from city 0 to city n - 1 after
 * processing the first i + 1 queries.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        Set<Integer>[] g = new Set[n];
        Arrays.setAll(g, i -> new HashSet<>());
        for (int i = 1; i < n; i++) g[i - 1].add(i);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            g[queries[i][0]].add(queries[i][1]);

            int[] dp = new int[n];
            Arrays.fill(dp, n + 1); dp[0] = 0;
            for (int j = 0; j < n; j++) {
                for (var next : g[j]) {
                    dp[next] = Math.min(dp[next], dp[j] + 1);
                }
            }

            ans[i] = dp[n - 1];
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
