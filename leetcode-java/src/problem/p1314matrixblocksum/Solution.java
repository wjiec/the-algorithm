package problem.p1314matrixblocksum;

import common.Checker;

import java.util.function.IntBinaryOperator;

/**
 * 1314. Matrix Block Sum
 *
 * https://leetcode.cn/problems/matrix-block-sum/
 *
 * Given a m x n matrix mat and an integer k, return a matrix answer where
 * each answer[i][j] is the sum of all elements mat[r][c] for:
 *
 * i - k <= r <= i + k,
 * j - k <= c <= j + k, and
 * (r, c) is a valid position in the matrix.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        IntBinaryOperator range = (int x, int y) -> sum[Math.max(Math.min(x, m), 0)][Math.max(Math.min(y, n), 0)];

        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = range.applyAsInt(i + k + 1, j + k + 1)
                    - range.applyAsInt(i - k, j + k + 1)
                    - range.applyAsInt(i + k + 1, j - k)
                    + range.applyAsInt(i - k, j - k);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().matrixBlockSum(new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        }, 1), new int[][]{
            {12,21,16},
            {27,45,33},
            {24,39,28}
        });

        assert Checker.check(new Solution().matrixBlockSum(new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        }, 2), new int[][]{
            {45,45,45},
            {45,45,45},
            {45,45,45}
        });
    }

}
