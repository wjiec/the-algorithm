package problem.p566reshapethematrix;

import java.util.Arrays;

/**
 * 566. Reshape the Matrix
 *
 * https://leetcode-cn.com/problems/reshape-the-matrix/
 *
 * In MATLAB, there is a handy function called reshape which can reshape an m x n matrix
 * into a new one with a different size r x c keeping its original data.
 *
 * You are given an m x n matrix mat and two integers r and c representing
 * the row number and column number of the wanted reshaped matrix.
 *
 * The reshaped matrix should be filled with all the elements of the
 * original matrix in the same row-traversing order as they were.
 *
 * If the reshape operation with given parameters is possible and legal,
 * output the new reshaped matrix; Otherwise, output the original matrix.
 */

public class Solution {

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int row = mat.length, col = mat[0].length, cnt = 0;
        if ((row * col != r * c) || (r == row && c == col)) {
            return mat;
        }

        int[][] rs = new int[r][c];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rs[cnt / c][cnt % c] = mat[i][j];
                cnt++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().matrixReshape(new int[][]{{1,2}, {3,4}}, 1, 4), new int[][]{{1,2,3,4}});
        assert Arrays.deepEquals(new Solution().matrixReshape(new int[][]{{1,2}, {3,4}}, 2, 4), new int[][]{{1,2}, {3,4}});
        assert Arrays.deepEquals(new Solution().matrixReshape(new int[][]{{1,2}}, 1, 1), new int[][]{{1, 2}});
    }

}
