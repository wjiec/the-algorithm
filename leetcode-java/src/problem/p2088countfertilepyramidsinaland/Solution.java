package problem.p2088countfertilepyramidsinaland;

/**
 * 2088. Count Fertile Pyramids in a Land
 *
 * https://leetcode.cn/problems/count-fertile-pyramids-in-a-land/
 *
 * A farmer has a rectangular grid of land with m rows and n columns that can be divided into unit cells.
 * Each cell is either fertile (represented by a 1) or barren (represented by a 0).
 * All cells outside the grid are considered barren.
 *
 * A pyramidal plot of land can be defined as a set of cells with the following criteria:
 *
 * The number of cells in the set has to be greater than 1 and all cells must be fertile.
 *
 * The apex of a pyramid is the topmost cell of the pyramid. The height of a pyramid is the
 * number of rows it covers. Let (r, c) be the apex of the pyramid, and its height be h.
 * Then, the plot comprises of cells (i, j) where r <= i <= r + h - 1 and c - (i - r) <= j <= c + (i - r).
 *
 * An inverse pyramidal plot of land can be defined as a set of cells with similar criteria:
 *
 * The number of cells in the set has to be greater than 1 and all cells must be fertile.
 *
 * The apex of an inverse pyramid is the bottommost cell of the inverse pyramid.
 * The height of an inverse pyramid is the number of rows it covers.
 * Let (r, c) be the apex of the pyramid, and its height be h.
 * Then, the plot comprises of cells (i, j) where r - h + 1 <= i <= r and c - (r - i) <= j <= c + (r - i).
 *
 * Given a 0-indexed m x n binary matrix grid representing the farmland, return the total
 * number of pyramidal and inverse pyramidal plots that can be found in grid.
 */

public class Solution {

    public int countPyramids(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = grid[i][j - 1] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] != 0) {
                    ans += pyramids(grid, i, j, 1, m, n);
                    ans += pyramids(grid, i, j, -1, m, n);
                }
            }
        }

        return ans;
    }

    private int pyramids(int[][] grid, int x, int y, int dx, int m, int n) {
        int ans = 0;
        while (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] >= 2 * (ans + 1) - 1) {
            ans++; x += dx; y += 1;
        }
        return ans - 1;
    }

    public static void main(String[] args) {
        assert new Solution().countPyramids(new int[][]{{0,1,1,0}, {1,1,1,1}}) == 2;
        assert new Solution().countPyramids(new int[][]{{1,1,1}, {1,1,1}}) == 2;
        assert new Solution().countPyramids(new int[][]{{1,0,1}, {0,0,0}, {1,0,1}}) == 0;
        assert new Solution().countPyramids(new int[][]{{1,1,1,1,0},{1,1,1,1,1},{1,1,1,1,1},{0,1,0,0,1}}) == 13;
    }

}
