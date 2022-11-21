package offer2.p99;

/**
 * 剑指 Offer II 099. 最小路径之和
 *
 * https://leetcode.cn/problems/0i0mDW/
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：一个机器人每次只能向下或者向右移动一步。
 */

public class Solution {

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 1; i < m; i++) grid[i][0] += grid[i - 1][0];
        for (int i = 1; i < n; i++) grid[0][i] += grid[0][i - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().minPathSum(new int[][]{
            {1,3,1},
            {1,5,1},
            {4,2,1}
        }) == 7;
        assert new Solution().minPathSum(new int[][]{{1,2,3},{4,5,6}}) == 12;
    }

}
