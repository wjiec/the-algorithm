package problem.p1139largest1borderedsquare;

/**
 * 1139. Largest 1-Bordered Square
 *
 * https://leetcode.cn/problems/largest-1-bordered-square/
 *
 * Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid
 * that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.
 */

public class Solution {

    public int largest1BorderedSquare(int[][] grid) {
        int ans = 0, l = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m - l; i++) {
            for (int j = 0; j < n - l; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, largestSquare(grid, i, j));
                    l = (int) Math.sqrt(ans);
                }
            }
        }
        return ans;
    }

    private int largestSquare(int[][] grid, int lx, int ly) {
        int ans = 1, m = grid.length, n = grid[0].length;
        for (int x = lx + 1, y = ly + 1; x < m && y < n; x++, y++) {
            if (grid[lx][y] != 1 || grid[x][ly] != 1) break;
            if (isSquare(grid, lx, ly, x, y)) {
                ans = (x - lx + 1) * (y - ly + 1);
            }
        }
        return ans;
    }

    private boolean isSquare(int[][] grid, int lx, int ly, int rx, int ry) {
        for (int x = lx; x <= rx; x++) { // right
            if (grid[x][ry] != 1) return false;
        }
        for (int y = ly; y <= ry; y++) { // bottom
            if (grid[rx][y] != 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().largest1BorderedSquare(new int[][]{
            {1,1,0},
            {1,1,1},
            {1,1,1},
            {1,1,1}
        }) == 9;

        assert new Solution().largest1BorderedSquare(new int[][]{
            {1,1,1},
            {1,0,1},
            {1,1,1}
        }) == 9;

        assert new Solution().largest1BorderedSquare(new int[][]{
            {1,1,0,0}
        }) == 1;

        assert new Solution().largest1BorderedSquare(new int[][]{
            {1,1,1,1,1,1,1,1,1},
            {1,0,1,1,0,0,0,1,1},
            {1,0,1,1,1,1,1,1,1},
            {1,1,1,1,0,0,0,0,0},
        }) == 16;
    }

}
