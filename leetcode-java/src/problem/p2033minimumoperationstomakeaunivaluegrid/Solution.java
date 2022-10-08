package problem.p2033minimumoperationstomakeaunivaluegrid;

import java.util.Arrays;

/**
 * 2033. Minimum Operations to Make a Uni-Value Grid
 *
 * https://leetcode.cn/problems/minimum-operations-to-make-a-uni-value-grid/
 *
 * You are given a 2D integer grid of size m x n and an integer x.
 * In one operation, you can add x to or subtract x from any element in the grid.
 *
 * A uni-value grid is a grid where all the elements of it are equal.
 *
 * Return the minimum number of operations to make the grid uni-value.
 * If it is not possible, return -1.
 */

public class Solution {

    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length, c = grid[0][0], i = 0;
        int[] sorted = new int[m * n];
        for (var row : grid) {
            for (var v : row) {
                if ((v - c) % x != 0) return -1;
                sorted[i++] = v;
            }
        }

        Arrays.sort(sorted);
        int mid = sorted[m * n / 2], ans = 0;
        for (var v : sorted) ans += Math.abs(v - mid) / x;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[][]{{2,4},{6,8}}, 2) == 4;
        assert new Solution().minOperations(new int[][]{{1,5},{2,3}}, 1) == 5;
        assert new Solution().minOperations(new int[][]{{1,2},{3,4}}, 2) == -1;
    }

}
