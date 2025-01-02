package weekly.w430.A;

/**
 * 3402. Minimum Operations to Make Columns Strictly Increasing
 *
 * https://leetcode.cn/contest/weekly-contest-430/problems/minimum-operations-to-make-columns-strictly-increasing/
 *
 * You are given a m x n matrix grid consisting of non-negative integers.
 *
 * In one operation, you can increment the value of any grid[i][j] by 1.
 *
 * Return the minimum number of operations needed to make all columns of grid strictly increasing.
 */

public class Solution {

    public int minimumOperations(int[][] grid) {
        int ans = 0;
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] <= grid[i - 1][j]) {
                    int diff = grid[i - 1][j] + 1 - grid[i][j];
                    ans += diff; grid[i][j] += diff;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
