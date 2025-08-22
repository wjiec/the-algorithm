package weekly.bw161.B;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Q2. Count Islands With Total Value Divisible by K
 *
 * https://leetcode.cn/contest/biweekly-contest-161/problems/count-islands-with-total-value-divisible-by-k/
 *
 * You are given an m x n matrix grid and a positive integer k.
 * An island is a group of positive integers (representing land) that are 4-directionally
 * connected (horizontally or vertically).
 *
 * The total value of an island is the sum of the values of all cells in the island.
 *
 * Return the number of islands with a total value divisible by k.
 */

public class Solution {

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int countIslands(int[][] grid, int k) {
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;

                int sum = grid[i][j];
                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[]{i, j}); grid[i][j] = 0;
                while (!q.isEmpty()) {
                    var curr = q.remove();
                    for (var dir : dirs) {
                        int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                        if (dx >= 0 && dx < m && dy >= 0 && dy < n && grid[dx][dy] != 0) {
                            q.add(new int[]{dx, dy}); sum += grid[dx][dy]; grid[dx][dy] = 0;
                        }
                    }
                }
                if (sum % k == 0) ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
