package weekly.bw137.C;

import java.util.Arrays;

/**
 * 3256. Maximum Value Sum by Placing Three Rooks I
 *
 * https://leetcode.cn/contest/biweekly-contest-137/problems/maximum-value-sum-by-placing-three-rooks-i/
 *
 * You are given a m x n 2D array board representing a chessboard, where board[i][j] represents
 * the value of the cell (i, j).
 *
 * Rooks in the same row or column attack each other. You need to place three rooks on the
 * chessboard such that the rooks do not attack each other.
 *
 * Return the maximum sum of the cell values on which the rooks are placed.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public long maximumValueSum(int[][] board) {
        int m = board.length, n = board[0].length;

        Integer[][] sorted = new Integer[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sorted[i][j] = j;
            }

            int ii = i;
            Arrays.sort(sorted[i], (a, b) -> board[ii][b] - board[ii][a]);
        }

        // 枚举三个车站在哪一行
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                for (int k = j + 1; k < m; k++) {
                    // 从3行的中找出3个不同的列, 使得值最大
                    ans = Math.max(ans, findMax(board, i, j, k, sorted));
                }
            }
        }

        return ans;
    }

    private long findMax(int[][] board, int i, int j, int k, Integer[][] sorted) {
        long ans = Long.MIN_VALUE;
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if (sorted[i][a].equals(sorted[j][b])) continue;

                for (int c = 0; c < 3; c++) {
                    if (sorted[k][c].equals(sorted[i][a]) || sorted[k][c].equals(sorted[j][b])) continue;

                    ans = Math.max(ans, (long) board[i][sorted[i][a]] + board[j][sorted[j][b]] + board[k][sorted[k][c]]);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
