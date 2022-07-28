package problem.p1334findthecitywiththesmallestnumberofneighborsatathresholddistance;

import java.util.Arrays;

/**
 * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 *
 * https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
 *
 * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti]
 * represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
 *
 * Return the city with the smallest number of cities that are reachable through some path and whose
 * distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
 *
 * Notice that the distance of a path connecting cities i and j is equal to
 * the sum of the edges' weights along that path.
 */

public class Solution {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(map[i], Integer.MAX_VALUE);
        for (var edge : edges) {
            map[edge[0]][edge[1]] = edge[2];
            map[edge[1]][edge[0]] = edge[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
                }
            }
        }

        int ans = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && map[i][j] <= distanceThreshold) {
                    c++;
                }
            }

            if (c <= min) {
                min = c; ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findTheCity(4, new int[][]{{0,1,3},{1,2,1},{1,3,4},{2,3,1}}, 4) == 3;
        assert new Solution().findTheCity(5, new int[][]{{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}}, 2) == 0;
        assert new Solution().findTheCity(5, new int[][]{{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}}, 2) == 0;
    }

}
