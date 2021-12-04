package problem.p2022convert1darrayinto2darray;

import java.util.Arrays;

/**
 * 2022. Convert 1D Array Into 2D Array
 *
 * https://leetcode-cn.com/problems/convert-1d-array-into-2d-array/
 *
 * You are given a 0-indexed 1-dimensional (1D) integer array original, and two integers, m and n.
 *
 * You are tasked with creating a 2-dimensional (2D) array with m rows and n columns
 * using all the elements from original.
 *
 * The elements from indices 0 to n - 1 (inclusive) of original
 * should form the first row of the constructed 2D array,
 * the elements from indices n to 2 * n - 1 (inclusive)
 * should form the second row of the constructed 2D array, and so on.
 *
 * Return an m x n 2D array constructed according to the above procedure,
 * or an empty 2D array if it is impossible.
 */

public class Solution {

    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) return new int[][]{};
        int[][] ans = new int[m][n];
        for (int i = 0; i < original.length; i++) {
            ans[i / n][i % n] = original[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().construct2DArray(new int[]{1,2,3,4}, 2, 2), new int[][]{
            {1,2}, {3,4}
        });

        assert Arrays.deepEquals(new Solution().construct2DArray(new int[]{1,2,3}, 1, 3), new int[][]{
            {1,2,3}
        });

        assert Arrays.deepEquals(new Solution().construct2DArray(new int[]{1,2}, 1, 1), new int[][]{
        });

        assert Arrays.deepEquals(new Solution().construct2DArray(new int[]{3}, 1, 2), new int[][]{
        });
    }

}
