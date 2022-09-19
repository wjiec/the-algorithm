package problem.p311sparsematrixmultiplication;

import common.Checker;
import common.Tag;

/**
 * 311. Sparse Matrix Multiplication
 *
 * https://leetcode.cn/problems/sparse-matrix-multiplication/
 *
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return
 * the result of mat1 x mat2. You may assume that multiplication is always possible.
 */

public class Solution {

    @Tag({"稀疏矩阵"})
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length, n = mat1[0].length, w = mat2[0].length;
        int[][] ans = new int[m][w];

        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (mat1[i][k] == 0) continue;

                for (int j = 0; j < w; j++) {
                    ans[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().multiply(new int[][]{
            {1,0,0},
            {-1,0,3}
        }, new int[][]{
            {7,0,0},
            {0,0,0},
            {0,0,1}
        }), new int[][]{{7,0,0},{-7,0,3}});

        assert Checker.check(new Solution().multiply(new int[][]{
            {0}
        }, new int[][]{
            {0}
        }), new int[][]{{0}});
    }

}
