package weekly.w387.B;

/**
 * 3070. Count Submatrices with Top-Left Element and Sum Less Than k
 *
 * https://leetcode.cn/contest/weekly-contest-387/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/
 *
 * You are given a 0-indexed integer matrix grid and an integer k.
 *
 * Return the number of submatrices that contain the top-left
 * element of the grid, and have a sum less than or equal to k.
 */

public class Solution {

    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, ans = 0;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + grid[i - 1][j - 1] - preSum[i - 1][j - 1];
                if (preSum[i][j] <= k) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
