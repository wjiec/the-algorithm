package weekly.w387.C;

import java.util.function.BiFunction;

/**
 * 3071. Minimum Operations to Write the Letter Y on a Grid
 *
 * https://leetcode.cn/contest/weekly-contest-387/problems/minimum-operations-to-write-the-letter-y-on-a-grid/
 *
 * You are given a 0-indexed n x n grid where n is odd, and grid[r][c] is 0, 1, or 2.
 *
 * We say that a cell belongs to the Letter Y if it belongs to one of the following:
 *
 * The diagonal starting at the top-left cell and ending at the center cell of the grid.
 * The diagonal starting at the top-right cell and ending at the center cell of the grid.
 * The vertical line starting at the center cell and ending at the bottom border of the grid.
 * The Letter Y is written on the grid if and only if:
 *
 * All values at cells belonging to the Y are equal.
 * All values at cells not belonging to the Y are equal.
 * The values at cells belonging to the Y are different from the values at cells not belonging to the Y.
 *
 * Return the minimum number of operations needed to write the letter Y on the grid
 * given that in one operation you can change the value at any cell to 0, 1, or 2.
 */

public class Solution {

    private int y0 = 0, y1 = 0, y2 = 0, ny0 = 0, ny1 = 0, ny2 = 0;

    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length, mid = n / 2;
        BiFunction<Integer, Integer, Integer> add = (ii, jj) -> {
            int i = ii, j = jj;
            if (i <= mid && ((i == j) || (i + j == n - 1))) {
                switch (grid[i][j]) {
                    case 0 -> y0++;
                    case 1 -> y1++;
                    case 2 -> y2++;
                }
            } else if (i > mid && j == mid) {
                switch (grid[i][j]) {
                    case 0 -> y0++;
                    case 1 -> y1++;
                    case 2 -> y2++;
                }
            } else {
                switch (grid[i][j]) {
                    case 0 -> ny0++;
                    case 1 -> ny1++;
                    case 2 -> ny2++;
                }
            }

            return 0;
        };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                add.apply(i, j);
            }
        }

        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, y1 + y2 + ny0 + ny2); // Y = 0, NY = 1
        ans = Math.min(ans, y1 + y2 + ny0 + ny1); // Y = 0, NY = 2
        ans = Math.min(ans, y0 + y2 + ny1 + ny2); // Y = 1, NY = 0
        ans = Math.min(ans, y0 + y2 + ny0 + ny1); // Y = 1, NY = 2
        ans = Math.min(ans, y0 + y1 + ny1 + ny2); // Y = 2, NY = 0
        ans = Math.min(ans, y0 + y1 + ny0 + ny2); // Y = 2, NY = 1
        return ans;
    }

    public static void main(String[] args) {
    }

}
