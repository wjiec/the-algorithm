package weekly.w438.B;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 3462. Maximum Sum With at Most K Elements
 *
 * https://leetcode.cn/contest/weekly-contest-438/problems/maximum-sum-with-at-most-k-elements/
 *
 * You are given a 2D integer matrix grid of size n x m, an integer array limits of length n, and an integer k.
 *
 * The task is to find the maximum sum of at most k elements from the matrix grid such that:
 *
 * The number of elements taken from the ith row of grid does not exceed limits[i].
 *
 * Return the maximum sum.
 */

public class Solution {

    public long maxSum(int[][] grid, int[] limits, int k) {
        int m = grid.length, n = grid[0].length;
        for (var row : grid) {
            Arrays.sort(row);
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                int t = row[l]; row[l] = row[r]; row[r] = t;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> grid[b[0]][b[1]] - grid[a[0]][a[1]]);
        for (int i = 0; i < m; i++) if (limits[i] != 0) pq.add(new int[]{i, 0});

        long ans = 0;
        for (int i = 0; i < k; i++) {
            var curr = pq.remove();
            ans += grid[curr[0]][curr[1]];
            if (curr[1] + 1 < limits[curr[0]] && curr[1] + 1 < n) {
                pq.add(new int[]{curr[0], curr[1] + 1});
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxSum(new int[][]{{1,2},{3,4}}, new int[]{1,2}, 2) == 7;
        assert new Solution().maxSum(new int[][]{{5,3,7},{8,2,6}}, new int[]{2,2}, 3) == 21;
    }

}
