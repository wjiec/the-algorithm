package weekly.bw97.D;

/**
 * 2556. Disconnect Path in a Binary Matrix by at Most One Flip
 *
 * https://leetcode.cn/problems/disconnect-path-in-a-binary-matrix-by-at-most-one-flip/
 *
 * You are given a 0-indexed m x n binary matrix grid. You can move from a cell (row, col)
 * to any of the cells (row + 1, col) or (row, col + 1) that has the value 1.
 *
 * The matrix is disconnected if there is no path from (0, 0) to (m - 1, n - 1).
 *
 * You can flip the value of at most one (possibly none) cell.
 * You cannot flip the cells (0, 0) and (m - 1, n - 1).
 *
 * Return true if it is possible to make the matrix disconnect or false otherwise.
 *
 * Note that flipping a cell changes its value from 0 to 1 or from 1 to 0.
 */

public class Solution {

    public boolean isPossibleToCutPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m * n <= 2) return false;

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isPossibleToCutPath(new int[][]{{1,1,1},{1,0,0},{1,1,1}});
        assert !new Solution().isPossibleToCutPath(new int[][]{{1,1,1},{1,0,1},{1,1,1}});
    }

}
