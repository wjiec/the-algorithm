package problem.p885spiralmatrixiii;

import common.PrettyPrinter;

import java.util.function.Function;

/**
 * 885. Spiral Matrix III
 *
 * https://leetcode.cn/problems/spiral-matrix-iii/
 *
 * You start at the cell (rStart, cStart) of an rows x cols grid facing east.
 * The northwest corner is at the first row and column in the grid,
 * and the southeast corner is at the last row and column.
 *
 * You will walk in a clockwise spiral shape to visit every position in this grid.
 * Whenever you move outside the grid's boundary, we continue our walk outside the grid
 * (but may return to the grid boundary later.). Eventually,
 * we reach all rows * cols spaces of the grid.
 *
 * Return an array of coordinates representing the positions of
 * the grid in the order you visited them.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] ans = new int[rows * cols][2];
        Function<Integer, Integer> xRange = (v) -> v >= rows ? rows - 1 : (v < 0 ? 0 : v);
        Function<Integer, Integer> yRange = (v) -> v >= cols ? cols - 1 : (v < 0 ? 0 : v);
        int x = rStart, y = cStart, lx = rStart - 1, ly = cStart - 1, rx = rStart + 1, ry = cStart + 1;
        for (int c = 0, n = Math.max(rows, cols), i = 0; c < n; c++) {
            if (x >= 0 && x < rows) { // top edge
                for (int j = yRange.apply(y); j < ry && j < cols; j++, i++) {
                    ans[i][0] = x; ans[i][1] = j;
                }
            }
            if (ry >= 0 && ry < cols) { // right edge
                for (int j = xRange.apply(x); j < rx && j < rows; j++, i++) {
                    ans[i][0] = j; ans[i][1] = ry;
                }
            }
            if (rx >= 0 && rx < rows) { // bottom
                for (int j = yRange.apply(ry); j > ly && j >= 0; j--, i++) {
                    ans[i][0] = rx; ans[i][1] = j;
                }
            }
            if (ly >= 0 && ly < cols) { // left edge
                for (int j = xRange.apply(rx); j > lx && j >= 0; j--, i++) {
                    ans[i][0] = j; ans[i][1] = ly;
                }
            }

            x = lx; y = ly; rx += 1; ry += 1; lx -= 1; ly -= 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().spiralMatrixIII(1, 4, 0, 0));
        PrettyPrinter.println(new Solution().spiralMatrixIII(5, 6, 1, 4));
    }

}
