package problem.p861scoreafterflippingmatrix;

/**
 * 861. Score After Flipping Matrix
 *
 * https://leetcode.cn/problems/score-after-flipping-matrix/
 *
 * You are given an m x n binary matrix grid.
 *
 * A move consists of choosing any row or column and toggling
 * each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
 *
 * Every row of the matrix is interpreted as a binary number,
 * and the score of the matrix is the sum of these numbers.
 *
 * Return the highest possible score after making any number of moves (including zero moves).
 */

public class Solution {

    public int matrixScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = m * (1 << (n - 1));

        for (int y = 1; y < n; y++) {
            int ones = 0;
            for (int[] row : grid) {
                if (row[0] == 1) ones += row[y];
                else ones += (1 - row[y]);
            }

            int k = Math.max(ones, m - ones);
            ans += k * (1 << (n - y - 1));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().matrixScore(new int[][]{
            {0,0,1,1},
            {1,0,1,0},
            {1,1,0,0}
        }) == 39;
        assert new Solution().matrixScore(new int[][]{{0}}) == 1;
    }

}
