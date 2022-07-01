package problem.p1091shortestpathinbinarymatrix;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1091. Shortest Path in Binary Matrix
 *
 * https://leetcode.cn/problems/shortest-path-in-binary-matrix/
 *
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
 * If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to
 * the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are
 * different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 */

public class Solution {

    private final int[][] dirs = new int[][]{
        {-1, -1}, {-1, +0}, {-1, +1},
        {+0, -1},           {+0, +1},
        {+1, -1}, {+1, +0}, {+1, +1}
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0) return -1;

        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n]; dp[0][0] = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        for (queue.add(new int[]{0, 0}); !queue.isEmpty(); ) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int x = curr[0] + dir[0], y = curr[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && dp[x][y] == 0) {
                    dp[x][y] = dp[curr[0]][curr[1]] + 1;
                    queue.add(new int[]{x, y});
                }
            }
        }

        return dp[m - 1][n - 1] == 0 ? -1 : dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        // [[0,1,1,0,0,0],[0,1,0,1,1,0],[0,1,1,0,1,0],[0,0,0,1,1,0],[1,1,1,1,1,0],[1,1,1,1,1,0]]
        assert new Solution().shortestPathBinaryMatrix(new int[][]{
            {0,1,1,0,0,0},
            {0,1,0,1,1,0},
            {0,1,1,0,1,0},
            {0,0,0,1,1,0},
            {1,1,1,1,1,0},
            {1,1,1,1,1,0},
        }) == 14;

        assert new Solution().shortestPathBinaryMatrix(new int[][]{{0,1},{1,0}}) == 2;
        assert new Solution().shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}) == 4;
        assert new Solution().shortestPathBinaryMatrix(new int[][]{{1,0,0},{1,1,0},{1,1,0}}) == -1;
    }

}
