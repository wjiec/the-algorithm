package problem.p1631pathwithminimumeffort;

import common.TODO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1631. Path With Minimum Effort
 *
 * https://leetcode.cn/problems/path-with-minimum-effort/
 *
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size
 * rows x columns, where heights[row][col] represents the height of cell (row, col).
 * You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right
 * cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and
 * you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive
 * cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    @TODO
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));
        pq.add(new int[]{0, 0, 0});

        int[][] distance = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        distance[0][0] = 0;

        boolean[][] visited = new boolean[m][n];
        while (!pq.isEmpty()) {
            int[] curr = pq.remove();
            int x = curr[0], y = curr[1], d = curr[2];
            if (visited[x][y]) continue;
            if (x == m - 1 && y == n - 1) break;

            visited[x][y] = true;
            for (var dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                    int md = Math.max(d, Math.abs(heights[x][y] - heights[dx][dy]));
                    if (md < distance[dx][dy]) {
                        distance[dx][dy] = md;
                        pq.add(new int[]{dx, dy, md});
                    }
                }
            }
        }
        return distance[m - 1][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().minimumEffortPath(new int[][]{{1,2,2},{3,8,2},{5,3,5}}) == 2;
        assert new Solution().minimumEffortPath(new int[][]{{1,2,3},{3,8,4},{5,3,5}}) == 1;
        assert new Solution().minimumEffortPath(new int[][]{
            {1,2,1,1,1},
            {1,2,1,2,1},
            {1,2,1,2,1},
            {1,2,1,2,1},
            {1,1,1,2,1}
        }) == 0;
    }

}
