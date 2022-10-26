package problem.p1905countsubislands;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1905. Count Sub Islands
 *
 * https://leetcode.cn/problems/count-sub-islands/
 *
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's
 * (representing water) and 1's (representing land). An island is a group of 1's
 * connected 4-directionally (horizontal or vertical). Any cells outside of the
 * grid are considered water cells.
 *
 * An island in grid2 is considered a sub-island if there is an island in grid1 that
 * contains all the cells that make up this island in grid2.
 *
 * Return the number of islands in grid2 that are considered sub-islands.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int ans = 0, m = grid1.length, n = grid1[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    if (contains(grid1, grid2, i, j)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private boolean contains(int[][] g1, int[][] g2, int x, int y) {
        boolean ans = g1[x][y] == 1; g2[x][y] = 0;
        int m = g1.length, n = g1[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (queue.add(new int[]{x, y}); !queue.isEmpty(); ) {
            int[] curr = queue.remove();
            int xx = curr[0], yy = curr[1];

            ans = ans && g1[xx][yy] == 1;
            for (var dir : dirs) {
                int dx = xx + dir[0], dy = yy + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && g2[dx][dy] == 1) {
                    g2[dx][dy] = 0;
                    queue.add(new int[]{dx, dy});
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countSubIslands(new int[][]{
            {1,1,1,0,0},
            {0,1,1,1,1},
            {0,0,0,0,0},
            {1,0,0,0,0},
            {1,1,0,1,1},
        }, new int[][]{
            {1,1,1,0,0},
            {0,0,1,1,1},
            {0,1,0,0,0},
            {1,0,1,1,0},
            {0,1,0,1,0}
        }) == 3;

        assert new Solution().countSubIslands(new int[][]{
            {1,0,1,0,1},
            {1,1,1,1,1},
            {0,0,0,0,0},
            {1,1,1,1,1},
            {1,0,1,0,1}
        }, new int[][]{
            {0,0,0,0,0},
            {1,1,1,1,1},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {1,0,0,0,1}
        }) == 2;
    }

}
