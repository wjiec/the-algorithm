package weekly.w449.B;

/**
 * Q2. Equal Sum Grid Partition I
 *
 * https://leetcode.cn/contest/weekly-contest-449/problems/equal-sum-grid-partition-i
 *
 * You are given an m x n matrix grid of positive integers. Your task is to determine
 * if it is possible to make either one horizontal or one vertical cut on the grid such that:
 *
 * Each of the two resulting sections formed by the cut is non-empty.
 * The sum of the elements in both sections is equal.
 *
 * Return true if such a partition exists; otherwise return false.©leetcode
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[] rows = new long[m], cols = new long[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
            }
        }

        for (int i = 1; i < m; i++) rows[i] += rows[i - 1];
        for (int j = 1; j < n; j++) cols[j] += cols[j - 1];

        for (int i = 0; i < m - 1; i++) {
            if (rows[i] * 2 == rows[m - 1]) return true;
        }
        for (int j = 0; j < n - 1; j++) {
            if (cols[j] * 2 == cols[n - 1]) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().canPartitionGrid(new int[][]{{42047},{57775},{99822}});
        assert new Solution().canPartitionGrid(new int[][]{{1,4},{2,3}});
    }

}
