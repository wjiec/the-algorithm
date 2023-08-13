package weekly.w357.C;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 2812. Find the Safest Path in a Grid
 *
 * https://leetcode.cn/contest/weekly-contest-357/problems/find-the-safest-path-in-a-grid/
 *
 * You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
 *
 * A cell containing a thief if grid[r][c] = 1
 * An empty cell if grid[r][c] = 0
 * You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell
 * in the grid, including cells containing thieves.
 *
 * The safeness factor of a path on the grid is defined as the minimum manhattan distance from
 * any cell in the path to any thief in the grid.
 *
 * Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).
 *
 * An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c)
 * and (r - 1, c) if it exists.
 *
 * The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|,
 * where |val| denotes the absolute value of val.
 */

public class Solution {

    private int n = 0;
    private final static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();
        int[][] distance = new int[n][n];
        for (var row : distance) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    distance[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            var curr = queue.remove();
            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < n && distance[dx][dy] == Integer.MAX_VALUE) {
                    distance[dx][dy] = distance[curr[0]][curr[1]] + 1;
                    queue.add(new int[]{dx, dy});
                }
            }
        }

        int l = 0, r = n + n + 1, ans = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(distance, mid)) {
                ans = mid; l = mid + 1;
            } else r = mid;
        }
        return ans;
    }

    private boolean check(int[][] distance, int factor) {
        if (distance[0][0] < factor) return false;

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new ArrayDeque<>();

        visited[0][0] = true;
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            var curr = queue.remove();
            int x = curr[0], y = curr[1];
            if (x == n - 1 && y == n - 1) {
                return true;
            }

            for (var dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < n && !visited[dx][dy] && distance[dx][dy] >= factor) {
                    visited[dx][dy] = true; queue.add(new int[]{dx, dy});
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().maximumSafenessFactor(List.of(
            List.of(1, 0, 0),
            List.of(0, 0, 0),
            List.of(0, 0, 1)
        )) == 0;

        assert new Solution().maximumSafenessFactor(List.of(
            List.of(0, 0, 1),
            List.of(0, 0, 0),
            List.of(0, 0, 0)
        )) == 2;

        assert new Solution().maximumSafenessFactor(List.of(
            List.of(0, 0, 0, 1),
            List.of(0, 0, 0, 0),
            List.of(0, 0, 0, 0),
            List.of(1, 0, 0, 0)
        )) == 2;
    }

}
