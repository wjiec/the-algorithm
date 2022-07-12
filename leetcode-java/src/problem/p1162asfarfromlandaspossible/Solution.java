package problem.p1162asfarfromlandaspossible;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1162. As Far from Land as Possible
 *
 * https://leetcode.cn/problems/as-far-from-land-as-possible/
 *
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find
 * a water cell such that its distance to the nearest land cell is maximized, and return the distance.
 *
 * If no land or water exists in the grid, return -1.
 *
 * The distance used in this problem is the Manhattan distance: the distance
 * between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maxDistance(int[][] grid) {
        // [x, y, d]
        Queue<int[]> queue = new ArrayDeque<>();

        int ans = -1, n = grid.length;
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (var dir : dirs) {
                        int x = i + dir[0], y = j + dir[1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                            queue.add(new int[]{i, j, 0});
                            visited[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1], d = curr[2];
            if (d > ans) ans = d;

            for (var dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n
                    && grid[nx][ny] == 0 && !visited[nx][ny]) {

                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, d + 1});
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxDistance(new int[][]{{1,0,1},{0,0,0},{1,0,1}}) == 2;
        assert new Solution().maxDistance(new int[][]{{1,0,0},{0,0,0},{0,0,0}}) == 4;
        assert new Solution().maxDistance(new int[][]{{0,0,0},{0,0,0},{0,0,0}}) == -1;
    }

}
