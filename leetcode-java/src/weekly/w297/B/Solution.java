package weekly.w297.B;

/**
 * 5270. Minimum Path Cost in a Grid
 *
 * https://leetcode.cn/contest/weekly-contest-297/problems/minimum-path-cost-in-a-grid/
 *
 * You are given a 0-indexed m x n integer matrix grid consisting of distinct integers from 0 to m * n - 1.
 * You can move in this matrix from a cell to any other cell in the next row. That is, if you are in cell (x, y)
 * such that x < m - 1, you can move to any of the cells (x + 1, 0), (x + 1, 1), ..., (x + 1, n - 1).
 * Note that it is not possible to move from cells in the last row.
 *
 * Each possible move has a cost given by a 0-indexed 2D array moveCost of size (m * n) x n, where
 * moveCost[i][j] is the cost of moving from a cell with value i to a cell in column j of the next row.
 * The cost of moving from cells in the last row of grid can be ignored.
 *
 * The cost of a path in grid is the sum of all values of cells visited plus the sum of costs of all
 * the moves made. Return the minimum cost of a path that starts from any cell in the first row and
 * ends at any cell in the last row.
 */

public class Solution {

    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;

        int[][] dp = new int[m][n];
        System.arraycopy(grid[0], 0, dp[0], 0, n);

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + moveCost[grid[i - 1][j]][j];
                for (int k = 0; k < n; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + moveCost[grid[i - 1][k]][j]);
                }
                dp[i][j] += grid[i][j];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minPathCost(new int[][]{{28,35,29,5,13,17,18,23,14},{30,15,12,27,2,26,25,19,7},{1,16,34,21,9,3,20,24,8},{4,33,22,11,31,0,6,10,32}}, new int[][]{{87,46,11,33,55,26,26,56,23},{77,56,72,49,35,18,37,66,37},{54,40,62,1,64,49,95,81,77},{80,7,76,71,91,67,75,84,52},{65,11,13,15,9,34,10,98,20},{1,95,100,61,33,47,28,100,44},{39,56,94,7,64,91,66,34,70},{37,99,62,7,23,78,74,89,97},{84,41,63,42,84,15,46,31,11},{60,36,27,25,37,18,4,90,43},{35,83,90,37,91,27,61,99,53},{85,2,98,99,67,70,38,91,68},{66,46,7,99,26,81,95,51,51},{41,96,66,84,61,73,78,28,63},{38,34,49,55,35,29,93,5,28},{3,30,80,20,23,10,93,40,33},{8,86,47,15,45,84,47,19,58},{72,5,76,82,60,50,13,74,38},{4,8,25,38,29,4,60,81,21},{65,50,74,32,9,47,71,55,14},{90,30,92,51,45,51,4,85,22},{30,56,1,45,63,72,91,73,60},{51,61,53,49,44,99,11,5,3},{24,54,91,11,5,30,50,89,44},{87,97,46,92,93,41,64,73,15},{94,76,90,80,30,9,88,8,33},{50,34,4,63,49,90,46,55,16},{10,46,80,21,97,69,70,85,31},{10,66,74,43,65,45,85,34,91},{82,26,77,10,2,5,89,39,4},{80,70,89,73,54,61,100,89,23},{30,66,80,51,3,34,92,100,63},{74,15,4,33,37,3,87,76,92},{44,43,77,99,27,1,23,10,8},{8,74,17,35,31,84,97,98,34},{99,9,28,43,55,39,93,64,93}}) == 59;

        assert new Solution().minPathCost(new int[][]{{5,3},{4,0},{2,1}}, new int[][]{{9,8},{1,5},{10,12},{18,6},{2,4},{14,3}}) == 17;
        assert new Solution().minPathCost(new int[][]{{5,1,2},{4,0,3}}, new int[][]{{12,10,15},{20,23,8},{21,7,1},{8,1,13},{9,10,25},{5,3,2}}) == 6;
    }

}
