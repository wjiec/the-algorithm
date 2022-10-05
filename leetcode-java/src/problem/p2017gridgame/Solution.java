package problem.p2017gridgame;

/**
 * 2017. Grid Game
 *
 * https://leetcode.cn/problems/grid-game/
 *
 * You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents
 * the number of points at position (r, c) on the matrix.
 *
 * Two robots are playing a game on this matrix.
 *
 * Both robots initially start at (0, 0) and want to reach (1, n-1).
 * Each robot may only move to the right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).
 *
 * At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all
 * the points from the cells on its path. For all cells (r, c) traversed
 * on the path, grid[r][c] is set to 0. Then, the second robot moves
 * from (0, 0) to (1, n-1), collecting the points on its path.
 *
 * Note that their paths may intersect with one another.
 *
 * The first robot wants to minimize the number of points collected by the second robot.
 * In contrast, the second robot wants to maximize the number of points it collects.
 * If both robots play optimally, return the number of points collected by the second robot.
 */

public class Solution {

    public long gridGame(int[][] grid) {
        int n = grid[0].length;

        long[] right = new long[n];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + grid[0][i + 1];
        }

        long[] left = new long[n];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + grid[1][i - 1];
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, Math.max(left[i], right[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().gridGame(new int[][]{{2,5,4},{1,5,1}}) == 4;
        assert new Solution().gridGame(new int[][]{{3,3,1},{8,5,2}}) == 4;
        assert new Solution().gridGame(new int[][]{{1,3,1,15},{1,3,3,1}}) == 7;
    }

}
