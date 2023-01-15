package weekly.w328.B;

import common.PrettyPrinter;

/**
 * 6292. Increment Submatrices by One
 *
 * https://leetcode.cn/problems/increment-submatrices-by-one/
 *
 * You are given a positive integer n, indicating that we initially
 * have an n x n 0-indexed integer matrix mat filled with zeroes.
 *
 * You are also given a 2D integer array query.
 * For each query[i] = [row1i, col1i, row2i, col2i], you should do the following operation:
 *
 * Add 1 to every element in the submatrix with the top left corner (row1i, col1i) and the
 * bottom right corner (row2i, col2i). That is, add 1 to mat[x][y] for for
 * all row1i <= x <= row2i and col1i <= y <= col2i.
 *
 * Return the matrix mat after performing every query.
 */

public class Solution {

    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] ans = new int[n][n];
        for (var query : queries) {
            for (int x = query[0]; x <= query[2]; x++) {
                ans[x][query[1]]++;
                if (query[3] + 1 < n) ans[x][query[3] + 1]--;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                ans[i][j] += ans[i][j - 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().rangeAddQueries(3, new int[][]{{1,1,2,2},{0,0,1,1}}));
    }

}
