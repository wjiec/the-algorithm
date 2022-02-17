package daily.d220217p688knightprobabilityinchessboard;

import common.Checker;

/**
 * 688. Knight Probability in Chessboard
 *
 * https://leetcode-cn.com/problems/knight-probability-in-chessboard/
 *
 * On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves.
 *
 * The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
 *
 * A chess knight has eight possible moves it can make, as illustrated below.
 *
 * Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly k moves or has moved off the chessboard.
 *
 * Return the probability that the knight remains on the board after it has stopped moving.
 */

public class Solution {

    private final int[][] dirs = new int[][]{
        {-2, -1}, {-2, 1}, // top
        {-1, 2}, {1, 2}, // right
        {2, 1}, {2, -1}, // bottom
        {1, -2}, {-1, -2} // left
    };

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        for (int a = 0; a <= k; a++) {
            for (int b = 0; b < n; b++) {
                for (int c = 0; c < n; c++) {
                    if (a == 0) {
                        dp[a][b][c] = 1;
                    } else {
                        for (int[] dir : dirs) {
                            int x = dir[0] + b, y = dir[1] + c;
                            if (x >= 0 && x < n && y >= 0 && y < n) {
                                dp[a][b][c] += dp[a - 1][x][y] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().knightProbability(3, 2, 0, 0), 0.0625);
        assert Checker.check(new Solution().knightProbability(1, 0, 0, 0), 1.0);
    }

}
