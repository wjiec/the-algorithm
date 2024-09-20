package weekly.bw139.B;

import java.util.*;

/**
 * 3286. Find a Safe Walk Through a Grid
 *
 * https://leetcode.cn/contest/biweekly-contest-139/problems/find-a-safe-walk-through-a-grid/
 *
 * You are given an m x n binary matrix grid and an integer health.
 *
 * You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).
 *
 * You can move up, down, left, or right from one cell to another adjacent cell as long as your health
 * remains positive.
 *
 * Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.
 *
 * Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.
 */

public class Solution {

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();

        boolean[][][] seen = new boolean[m][n][health + 1];
        seen[0][0][health - grid.get(0).get(0)] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, health - grid.get(0).get(0)});

        while (!q.isEmpty()) {
            var curr = q.remove();
            int x = curr[0], y = curr[1], h = curr[2];
            if (x == m - 1 && y == n - 1 && h > 0) return true;

            for (var dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                    int dh = h - grid.get(dx).get(dy);
                    if (dh > 0 && !seen[dx][dy][dh]) {
                        seen[dx][dy][dh] = true;
                        q.add(new int[]{dx, dy, dh});
                    }
                }
            }
        }

        return false;
    }

    private static class BFS {
        private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public boolean findSafeWalk(List<List<Integer>> grid, int health) {
            int m = grid.size(), n = grid.get(0).size();

            int[][] dis = new int[m][n];
            for (var row : dis) Arrays.fill(row, m * n);
            dis[0][0] = grid.get(0).get(0);

            Deque<int[]> dq = new ArrayDeque<>();
            dq.addFirst(new int[]{0, 0});

            while (!dq.isEmpty()) {
                var curr = dq.removeFirst();
                int x = curr[0], y = curr[1];
                if (dis[x][y] > health) continue;

                for (var dir : dirs) {
                    int dx = x + dir[0], dy = y + dir[1];
                    if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                        int dv = grid.get(dx).get(dy);
                        if (dis[x][y] + dv < dis[dx][dy]) {
                            dis[dx][dy] = dis[x][y] + dv;
                            if (dv == 0) dq.addFirst(new int[]{dx, dy});
                            else dq.addLast(new int[]{dx, dy});
                        }
                    }
                }
            }

            return dis[m - 1][n - 1] < health;
        }
    }

    public static void main(String[] args) {
        assert !new Solution().findSafeWalk(List.of(
            List.of(0,1,1,0,0,0),
            List.of(1,0,1,0,0,0),
            List.of(0,1,1,1,0,1),
            List.of(0,0,1,0,1,0)
        ), 3);

        assert !new BFS().findSafeWalk(List.of(
            List.of(0,1,1,0,0,0),
            List.of(1,0,1,0,0,0),
            List.of(0,1,1,1,0,1),
            List.of(0,0,1,0,1,0)
        ), 3);
    }

}
