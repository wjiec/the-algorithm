package weekly.w449.D;

import java.util.HashMap;
import java.util.Map;

/**
 * Q4. Equal Sum Grid Partition II
 *
 * https://leetcode.cn/contest/weekly-contest-449/problems/equal-sum-grid-partition-ii
 *
 * You are given an m x n matrix grid of positive integers. Your task is to determine
 * if it is possible to make either one horizontal or one vertical cut on the grid such that:
 *
 * Each of the two resulting sections formed by the cut is non-empty.
 *
 * The sum of elements in both sections is equal, or can be made equal by discounting
 * at most one single cell in total (from either section).
 *
 * If a cell is discounted, the rest of the section must remain connected.
 *
 * Return true if such a partition exists; otherwise, return false.
 *
 * Note: A section is connected if every cell in it can be reached from any
 * other cell by moving up, down, left, or right through other cells in the section.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    public boolean canPartitionGrid(int[][] grid) {
        Map<Long, Integer> freq = new HashMap<>();
        int m = grid.length, n = grid[0].length;
        long[] rows = new long[m], cols = new long[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
                freq.merge((long) grid[i][j], 1, Integer::sum);
            }
        }

        for (int i = 1; i < m; i++) rows[i] += rows[i - 1];
        for (int j = 1; j < n; j++) cols[j] += cols[j - 1];

        // 接下来需要找到一条线, 使得左右或者上下两部分相等(或者相差的部分可以在多的那边去掉)
        //  - 如果某一个部分是 k * 1 的纬度, 那么这个去掉的值只能在头尾

        Map<Long, Integer> curr = new HashMap<>();
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) curr.merge((long) grid[i][j], 1, Integer::sum);
            long l = rows[i], r = rows[m - 1] - rows[i];
            if (l == r) return true;

            long diff = Math.abs(l - r);
            if (l > r && curr.containsKey(diff)) { // 可以从左边删除
                // 需要在<上边>删除一个元素, 我们需要检查这个元素的位置是否符合要求
                //  - 如果只有一行或者一列那么只能在角落
                if ((i != 0 && n != 1) || horizontal(grid, 0, 0, i, n - 1, diff)) return true;
            } else if (r > l && freq.getOrDefault(diff, 0) > curr.getOrDefault(diff, 0)) {
                // 需要在<下边>删除一个元素
                if ((i != m - 2 && n != 1) || horizontal(grid, i + 1, 0, m - 1, n - 1, diff)) return true;
            }
        }

        curr.clear();
        for (int j = 0; j < n - 1; j++) {
            for (int[] row : grid) curr.merge((long) row[j], 1, Integer::sum);
            long l = cols[j], r = cols[n - 1] - cols[j];
            if (l == r) return true;

            long diff = Math.abs(l - r);
            if (l > r && curr.containsKey(diff)) {
                // 需要在左边删除一个元素
                if ((j != 0 && m != 1) || vertical(grid, 0, 0, m - 1, j, diff)) return true;
            } else if (r > l && freq.getOrDefault(diff, 0) > curr.getOrDefault(diff, 0)) {
                // 需要在右边删除一个元素
                if ((j != n - 2 && m != 1) || vertical(grid, 0, j + 1, m - 1, n - 1, diff)) return true;
            }
        }

        return false;
    }

    private boolean horizontal(int[][] grid, int x1, int y1, int x2, int y2, long v) {
        return grid[x1][y1] == v || grid[x1][y2] == v || grid[x2][y1] == v || grid[x2][y2] == v;
    }

    private boolean vertical(int[][] grid, int x1, int y1, int x2, int y2, long v) {
        return grid[x1][y1] == v || grid[x2][y1] == v || grid[x1][y2] == v || grid[x2][y2] == v;
    }

    public static void main(String[] args) {
        assert !new Solution().canPartitionGrid(new int[][]{{10},{5},{4},{5}});
        assert !new Solution().canPartitionGrid(new int[][]{{10,5,4,5}});
    }

}
