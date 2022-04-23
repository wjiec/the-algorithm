package problem.p576outofboundarypaths;

/**
 * 576. Out of Boundary Paths
 *
 * https://leetcode-cn.com/problems/out-of-boundary-paths/
 *
 * There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn].
 * You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid
 * crossing the grid boundary).
 *
 * You can apply at most maxMove moves to the ball.
 *
 * Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to
 * move the ball out of the grid boundary.
 *
 * Since the answer can be very large, return it modulo 109 + 7.
 */

public class Solution {

    private final int MOD = (int) (1e9 + 7);
    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int ans = 0;
        int[][][] dp = new int[maxMove + 1][m][n];
        dp[0][startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int c = dp[i][j][k];
                    if (c > 0) {
                        for (int[] dir : dirs) {
                            int x = j + dir[0], y = k + dir[1];
                            if (x >= 0 && x < m && y >= 0 && y < n) {
                                dp[i + 1][x][y] = (dp[i + 1][x][y] + c) % MOD;
                            } else {
                                ans = (ans + c) % MOD;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findPaths(2, 2, 2, 0, 0) == 6;
        assert new Solution().findPaths(1, 3, 3, 0, 1) == 12;
    }

}
