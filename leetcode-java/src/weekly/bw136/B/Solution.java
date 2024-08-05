package weekly.bw136.B;

/**
 * 100387. Minimum Number of Flips to Make Binary Grid Palindromic I
 *
 * https://leetcode.cn/contest/biweekly-contest-136/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-i/
 *
 * You are given an m x n binary matrix grid.
 *
 * A row or column is considered palindromic if its values read the same forward and backward.
 *
 * You can flip any number of cells in grid from 0 to 1, or from 1 to 0.
 *
 * Return the minimum number of cells that need to be flipped to make either
 * all rows palindromic or all columns palindromic.
 */

public class Solution {

    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int rowAns = 0;
        for (int[] row : grid) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                rowAns += row[l] ^ row[r];
            }
        }

        int colAns = 0;
        for (int j = 0; j < n; j++) {
            for (int l = 0, r = m - 1; l < r; l++, r--) {
                colAns += grid[l][j] ^ grid[r][j];
            }
        }

        return Math.min(rowAns, colAns);
    }

    public static void main(String[] args) {
    }

}
