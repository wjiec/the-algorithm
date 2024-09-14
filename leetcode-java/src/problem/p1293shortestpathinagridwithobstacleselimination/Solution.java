package problem.p1293shortestpathinagridwithobstacleselimination;

import common.Tag;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 *
 * https://leetcode.cn/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 *
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
 *
 * You can move up, down, left, or right from and to an empty cell in one step.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower
 * right corner (m - 1, n - 1) given that you can eliminate at most k obstacles.
 *
 * If it is not possible to find such walk return -1.
 */

public class Solution {

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    @Tag("多一个纬度的BFS")
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) return 0;
        // 假设网格中没有障碍物, 那么最短路径就是 m + n - 2 (去掉起点和终点)
        // 如果允许消除的障碍物的数量 >= m + n - 3, 那么就一定可以走这个最短路径
        //  从起点到终点, 一共有 m + n - 1 个格子, 去掉起点和终点
        //  后剩下 m - n - 3 个可为障碍物的格子
        if (k >= m + n - 3) return m + n - 2;

        // [x, y, eliminated]
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] seen = new boolean[m][n][k + 1];

        q.add(new int[]{0, 0, 0});
        seen[0][0][0] = true;

        for (int step = 1; !q.isEmpty(); step++) {
            for (int i = 0, qc = q.size(); i < qc; i++) {
                var curr = q.remove();
                int x = curr[0], y = curr[1], e = curr[2];
                for (var dir : dirs) {
                    int dx = x + dir[0], dy = y + dir[1];
                    if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                        if (grid[dx][dy] == 0 && !seen[dx][dy][e]) {
                            if (dx == m - 1 && dy == n - 1) return step;

                            seen[dx][dy][e] = true;
                            q.add(new int[]{dx, dy, e});
                        }

                        if (grid[dx][dy] == 1 && e < k && !seen[dx][dy][e + 1]) {
                            seen[dx][dy][e + 1] = true;
                            q.add(new int[]{dx, dy, e + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
    }

}
