package problem.p1727largestsubmatrixwithrearrangements;

import common.TODO;
import common.Tag;

import java.util.Arrays;

/**
 * 1727. Largest Submatrix With Rearrangements
 *
 * https://leetcode.cn/problems/largest-submatrix-with-rearrangements/
 *
 * You are given a binary matrix matrix of size m x n, and you are allowed to
 * rearrange the columns of the matrix in any order.
 *
 * Return the area of the largest submatrix within matrix where every element of
 * the submatrix is 1 after reordering the columns optimally.
 */

public class Solution {

    @TODO
    @Tag({"矩阵", "排列", "最大矩形"})
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    // 记录一列上连续 1 的个数
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        int ans = 0;
        for (int[] row : matrix) {
            Arrays.sort(row);
            for (int j = n - 1; j >= 0; j--) {
                ans = Math.max(ans, row[j] * (n - j));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestSubmatrix(new int[][]{{0,0,1},{1,1,1},{1,0,1}}) == 4;
        assert new Solution().largestSubmatrix(new int[][]{{1,0,1,0,1}}) == 3;
        assert new Solution().largestSubmatrix(new int[][]{{1,1,0},{1,0,1}}) == 2;
        assert new Solution().largestSubmatrix(new int[][]{{0,0},{0,0}}) == 0;
    }

}
