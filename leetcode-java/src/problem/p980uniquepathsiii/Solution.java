package problem.p980uniquepathsiii;

/**
 * 980. Unique Paths III
 *
 * https://leetcode.cn/problems/unique-paths-iii/
 *
 * You are given an m x n integer array grid where grid[i][j] could be:
 *
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 *
 * Return the number of 4-directional walks from the starting square to the ending
 * square, that walk over every non-obstacle square exactly once.
 */

public class Solution {

    private int ans = 0;

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int x = 0, y = 0, blank = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    x = i; y = j;
                } else if (grid[i][j] == 0) {
                    blank++;
                }
            }
        }

        dfs(grid, x, y, blank);
        return ans;
    }

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void dfs(int[][] grid, int x, int y, int remain) {
        int m = grid.length, n = grid[0].length;
        for (var dir : dirs) {
            int dx = x + dir[0], dy = y + dir[1];
            if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                if (grid[dx][dy] == 0) {
                    grid[dx][dy] = -2;
                    dfs(grid, dx, dy, remain - 1);
                    grid[dx][dy] = 0;
                } else if (grid[dx][dy] == 2 && remain == 0) {
                    ans++;
                }
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().uniquePathsIII(new int[][]{
            {1,0,0,0},
            {0,0,0,0},
            {0,0,2,-1}
        }) == 2;

        assert new Solution().uniquePathsIII(new int[][]{
            {1,0,0,0},
            {0,0,0,0},
            {0,0,0,2}
        }) == 4;

        assert new Solution().uniquePathsIII(new int[][]{
            {0,1},
            {2,0}
        }) == 0;
    }

}
