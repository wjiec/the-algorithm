package problem.p1254numberofclosedislands;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1254. Number of Closed Islands
 *
 * https://leetcode.cn/problems/number-of-closed-islands/
 *
 * Given a 2D grid consists of 0s (land) and 1s (water). An island is a maximal 4-directionally connected
 * group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 */

public class Solution {

    public int closedIsland(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && bfs(grid, i, j)) ans++;
            }
        }
        return ans;
    }

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private boolean bfs(int[][] grid, int x, int y) {
        boolean isLand = true;
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (queue.add(new int[]{x, y}); !queue.isEmpty(); ) {
            int[] curr = queue.remove();
            int a = curr[0], b = curr[1];
            grid[a][b] = 2;

            isLand = isLand && !(a == 0 || a == m - 1 || b == 0 || b == n - 1);
            for (var dir : dirs) {
                int nx = a + dir[0], ny = b + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                    grid[nx][ny] = 2;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return isLand;
    }

    public static void main(String[] args) {
        assert new Solution().closedIsland(new int[][]{
            {1,1,1,1,1,1,1,0},
            {1,0,0,0,0,1,1,0},
            {1,0,1,0,1,1,1,0},
            {1,0,0,0,0,1,0,1},
            {1,1,1,1,1,1,1,0},
        }) == 2;

        assert new Solution().closedIsland(new int[][]{
            {0,0,1,0,0},
            {0,1,0,1,0},
            {0,1,1,1,0}
        }) == 1;

        assert new Solution().closedIsland(new int[][]{
            {1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1},
            {1,0,1,1,1,0,1},
            {1,0,1,0,1,0,1},
            {1,0,1,1,1,0,1},
            {1,0,0,0,0,0,1},
            {1,1,1,1,1,1,1}
        }) == 2;
    }

}
