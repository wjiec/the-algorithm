package problem.p867transposematrix;

import java.util.Arrays;

/**
 * 867. Transpose Matrix
 *
 * https://leetcode-cn.com/problems/transpose-matrix/
 *
 * Given a 2D integer array matrix, return the transpose of matrix.
 *
 * The transpose of a matrix is the matrix flipped over its main diagonal,
 * switching the matrix's row and column indices.
 */

public class Solution {

    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][i] = matrix[i][j];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().transpose(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}}), new int[][]{{1,4,7}, {2,5,8}, {3,6,9}});
        assert Arrays.deepEquals(new Solution().transpose(new int[][]{{1,2,3}, {4,5,6}}), new int[][]{{1,4}, {2,5}, {3,6}});
    }

}
