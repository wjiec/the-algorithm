package weekly.w334.D;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2577. Minimum Time to Visit a Cell In a Grid
 *
 * https://leetcode.cn/problems/minimum-time-to-visit-a-cell-in-a-grid/
 *
 * You are given a m x n matrix grid consisting of non-negative integers
 * where grid[row][col] represents the minimum time required to be able
 * to visit the cell (row, col), which means you can visit the cell (row, col)
 * only when the time you visit it is greater than or equal to grid[row][col].
 *
 * You are standing in the top-left cell of the matrix in the 0th second, and
 * you must move to any adjacent cell in the four directions: up, down, left,
 * and right. Each move you make takes 1 second.
 *
 * Return the minimum time required in which you can visit the bottom-right cell of the matrix.
 * If you cannot visit the bottom-right cell, then return -1.
 */

public class Solution {

    private final static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minimumTime(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][1] > 1 && grid[1][0] > 1) return -1;

        int[][] distance = new int[m][n];
        for (var row : distance) Arrays.fill(row, Integer.MAX_VALUE);
        distance[0][0] = 0;

        // [x, y, distance]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.remove();
            int x = curr[0], y = curr[1], d = curr[2];
            if (x == m - 1 && y == n - 1) return d;

            for (var dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                    int nd = Math.max(d + 1, grid[dx][dy]);
                    nd += (nd - dx - dy) % 2; // 同奇偶性
                    if (nd < distance[dx][dy]) {
                        distance[dx][dy] = nd;
                        pq.add(new int[]{dx, dy, nd});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().minimumTime(new int[][]{{0,1,3,2},{5,1,2,5},{4,3,8,6}}) == 7;
        assert new Solution().minimumTime(new int[][]{{0,2,4},{3,2,1},{1,0,4}}) == -1;
    }

}
