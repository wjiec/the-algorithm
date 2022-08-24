package problem.p1605findvalidmatrixgivenrowandcolumnsums;

import common.PrettyPrinter;

/**
 * 1605. Find Valid Matrix Given Row and Column Sums
 *
 * https://leetcode.cn/problems/find-valid-matrix-given-row-and-column-sums/
 *
 * You are given two arrays rowSum and colSum of non-negative integers where rowSum[i] is the
 * sum of the elements in the ith row and colSum[j] is the sum of the elements of the jth
 * column of a 2D matrix. In other words, you do not know the elements of the matrix, but
 * you do know the sums of each row and column.
 *
 * Find any matrix of non-negative integers of size rowSum.length x colSum.length that satisfies
 * the rowSum and colSum requirements.
 *
 * Return a 2D array representing any matrix that fulfills the requirements. It's guaranteed
 * that at least one matrix that fulfills the requirements exists.
 */

public class Solution {

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rows = rowSum.length, cols = colSum.length;
        int[][] matrix = new int[rows][cols];
        for (int i = 0, j = 0; i < rows && j < cols; ) {
            if (rowSum[i] < colSum[j]) {
                matrix[i][j] = rowSum[i];
                colSum[j] -= rowSum[i++];
            } else {
                matrix[i][j] = colSum[j];
                rowSum[i] -= colSum[j++];
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().restoreMatrix(new int[]{3,8}, new int[]{4,7}));
        PrettyPrinter.println(new Solution().restoreMatrix(new int[]{5,7,10}, new int[]{8,6,8}));
        PrettyPrinter.println(new Solution().restoreMatrix(new int[]{14,9}, new int[]{6,9,8}));
        PrettyPrinter.println(new Solution().restoreMatrix(new int[]{1,0}, new int[]{1}));
        PrettyPrinter.println(new Solution().restoreMatrix(new int[]{0}, new int[]{0}));

        PrettyPrinter.println(new Solution().restoreMatrix(new int[]{6,15,24}, new int[]{12,15,18}));
    }

}
