package daily.d240619p2713maximumstrictlyincreasingcellsinamatrix;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 2713. Maximum Strictly Increasing Cells in a Matrix
 *
 * https://leetcode.cn/problems/maximum-strictly-increasing-cells-in-a-matrix
 *
 * Given a 1-indexed m x n integer matrix mat, you can select any cell in the matrix as your starting cell.
 *
 * From the starting cell, you can move to any other cell in the same row or column, but only
 * if the value of the destination cell is strictly greater than the value of the current cell.
 *
 * You can repeat this process as many times as possible, moving from cell to cell until you
 * can no longer make any moves.
 *
 * Your task is to find the maximum number of cells that you can visit in the matrix by starting from some cell.
 *
 * Return an integer denoting the maximum number of cells that can be visited.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] sorted = new int[m * n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sorted[i * n + j][0] = i;
                sorted[i * n + j][1] = j;
            }
        }
        Arrays.sort(sorted, (a, b) -> mat[b[0]][b[1]] - mat[a[0]][a[1]]);

        TreeMap<Integer, Integer>[] rows = new TreeMap[m];
        TreeMap<Integer, Integer>[] cols = new TreeMap[n];
        Arrays.setAll(rows, i -> new TreeMap<>());
        Arrays.setAll(cols, i -> new TreeMap<>());
        for (var row : rows) row.put(Integer.MAX_VALUE, 0);
        for (var col : cols) col.put(Integer.MAX_VALUE, 0);

        int ans = 0;
        for (var item : sorted) {
            int x = item[0], y = item[1];
            int v = mat[x][y], curr = 0;

            curr = Math.max(curr, rows[x].higherEntry(v).getValue() + 1);
            curr = Math.max(curr, cols[y].higherEntry(v).getValue() + 1);

            rows[x].merge(v, curr, Integer::max);
            cols[y].merge(v, curr, Integer::max);

            ans = Math.max(ans, curr);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxIncreasingCells(new int[][]{
            { 5, 2,  9},
            {-6, 2, -5},
            {-1, 0,  5}
        }) == 6; // -6, -1, 0, 2, 5, 9

        assert new Solution().maxIncreasingCells(new int[][]{
            {-9,  4, -8},
            { 3, -4, -8}
        }) == 3;

        assert new Solution().maxIncreasingCells(new int[][]{{3,1},{3,4}}) == 2;
        assert new Solution().maxIncreasingCells(new int[][]{{1,1},{1,1}}) == 1;
        assert new Solution().maxIncreasingCells(new int[][]{{3,1,6},{-9,5,7}}) == 4;
    }

}
