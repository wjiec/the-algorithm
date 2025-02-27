package weekly.w436.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 3446. Sort Matrix by Diagonals
 *
 * https://leetcode.cn/contest/weekly-contest-436/problems/sort-matrix-by-diagonals/
 *
 * You are given an n x n square matrix of integers grid. Return the matrix such that:
 *
 * The diagonals in the bottom-left triangle (including the middle diagonal) are sorted in non-increasing order.
 *
 * The diagonals in the top-right triangle are sorted in non-decreasing order.
 */

public class Solution {

    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            sort(grid, i, 0, -1);
            if (i != 0) sort(grid, 0, i, 1);
        }
        return grid;
    }

    private void sort(int[][] grid, int x, int y, int cmp) {
        int n = grid.length;
        List<Integer> all = new ArrayList<>();
        for (int dx = x, dy = y; dx <n && dy < n; dx++, dy++) all.add(grid[dx][dy]);
        all.sort((a, b) -> Integer.compare(a, b) * cmp);

        for (int dx = x, dy = y, i = 0; dx <n && dy < n; dx++, dy++) grid[dx][dy] = all.get(i++);
    }

    public static void main(String[] args) {
    }

}
