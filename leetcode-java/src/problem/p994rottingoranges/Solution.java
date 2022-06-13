package problem.p994rottingoranges;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 *
 * https://leetcode.cn/problems/rotting-oranges/
 *
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int orangesRotting(int[][] grid) {
        int empty = 0, fresh = 0, ans = 0;
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) fresh++;
                else queue.add(new int[]{i, j});
            }
        }

        if (empty == m * n) return 0;
        if (queue.isEmpty()) return -1;
        if (queue.size() + empty == m * n) return 0;

        while (fresh > 0) {
            int count = rotten(grid, queue);
            ans++;
            fresh -= count;
            if (count == 0) break;
        }
        return fresh == 0 ? ans : -1;
    }

    private int rotten(int[][] grid, Queue<int[]> queue) {
        int m = grid.length, n = grid[0].length;
        int count = 0, round = queue.size();
        for (int i = 0; i < round; i++) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int x = curr[0] + dir[0], y = curr[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (grid[x][y] == 1) {
                        count++;
                        grid[x][y] = 2;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        assert new Solution().orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}) == 4;
        assert new Solution().orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}}) == -1;
        assert new Solution().orangesRotting(new int[][]{{0,2}}) == 0;
    }

}
