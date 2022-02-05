package daily.d220205p1219pathwithmaximumgold;

/**
 * 1219. Path with Maximum Gold
 *
 * https://leetcode-cn.com/problems/path-with-maximum-gold/
 *
 * In a gold mine grid of size m x n, each cell in this mine has an integer representing
 * the amount of gold in that cell, 0 if it is empty.
 *
 * Return the maximum amount of gold you can collect under the conditions:
 *
 * Every time you are located in a cell you will collect all the gold in that cell.
 * From your position, you can walk one step to the left, right, up, or down.
 * You can't visit the same cell more than once.
 * Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 */

public class Solution {

    private int ans = 0;
    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    int temp = grid[i][j];
                    grid[i][j] = 0;
                    dfs(grid, i, j, temp);
                    grid[i][j] = temp;
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int x, int y, int sum) {
        int temp = 0;
        for (var dir : dirs) {
            int a = x + dir[0], b = y + dir[1];
            if (0 <= a && a < grid.length && 0 <= b && b < grid[0].length && grid[a][b] != 0) {
                temp = grid[a][b];
                grid[a][b] = 0;
                dfs(grid, a, b, sum + temp);
                grid[a][b] = temp;
            }
        }

        if (temp == 0) ans = Math.max(ans, sum);
    }

    public static void main(String[] args) {
        assert new Solution().getMaximumGold(new int[][]{
            {0,6,0},
            {5,8,7},
            {0,9,0}
        }) == 24;

        assert new Solution().getMaximumGold(new int[][]{
            {1,0,7},
            {2,0,6},
            {3,4,5},
            {0,3,0},
            {9,0,20}
        }) == 28;
    }

}
