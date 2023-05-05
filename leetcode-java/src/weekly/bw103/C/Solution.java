package weekly.bw103.C;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 2658. Maximum Number of Fish in a Grid
 *
 * https://leetcode.cn/contest/biweekly-contest-103/problems/maximum-number-of-fish-in-a-grid/
 *
 * You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:
 *
 * A land cell if grid[r][c] = 0, or
 * A water cell containing grid[r][c] fish, if grid[r][c] > 0.
 * A fisher can start at any water cell (r, c) and can do the following operations any number of times:
 *
 * Catch all the fish at cell (r, c), or
 * Move to any adjacent water cell.
 * Return the maximum number of fish the fisher can catch if he chooses his starting
 * cell optimally, or 0 if no water cell exists.
 *
 * An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1),
 * (r + 1, c) or (r - 1, c) if it exists.
 */

public class Solution {

    public int findMaxFish(int[][] grid) {
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    ans = Math.max(ans, findFish(grid, i, j));
                }
            }
        }
        return ans;
    }

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int findFish(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        int ans = grid[x][y]; grid[x][y] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && grid[dx][dy] > 0) {
                    ans += grid[dx][dy]; grid[dx][dy] = 0;
                    queue.add(new int[]{dx, dy});
                }
            }
        }
        return ans;
    }

}
