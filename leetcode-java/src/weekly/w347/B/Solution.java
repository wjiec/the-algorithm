package weekly.w347.B;

import java.util.HashSet;
import java.util.Set;

/**
 * 2711. Difference of Number of Distinct Values on Diagonals
 *
 * https://leetcode.cn/contest/weekly-contest-347/problems/difference-of-number-of-distinct-values-on-diagonals/
 *
 * Given a 0-indexed 2D grid of size m x n, you should find the matrix answer of size m x n.
 *
 * The value of each cell (r, c) of the matrix answer is calculated in the following way:
 *
 * Let topLeft[r][c] be the number of distinct values in the top-left diagonal of the cell (r, c) in the matrix grid.
 * Let bottomRight[r][c] be the number of distinct values in the bottom-right diagonal of the cell (r, c) in the matrix grid.
 * Then answer[r][c] = |topLeft[r][c] - bottomRight[r][c]|.
 *
 * Return the matrix answer.
 *
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost
 * row or leftmost column and going in the bottom-right direction until reaching the matrix's end.
 *
 * A cell (r1, c1) belongs to the top-left diagonal of the cell (r, c), if both belong to the
 * same diagonal and r1 < r. Similarly is defined bottom-right diagonal.
 */

public class Solution {

    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                Set<Integer> topLeft = new HashSet<>();
                for (int dx = x - 1, dy = y - 1; dx >= 0 && dy >= 0; dx--, dy--) topLeft.add(grid[dx][dy]);
                Set<Integer> rightBottom = new HashSet<>();
                for (int dx = x + 1, dy = y + 1; dx < m && dy < n; dx++, dy++) rightBottom.add(grid[dx][dy]);
                ans[x][y] = Math.abs(topLeft.size() - rightBottom.size());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
