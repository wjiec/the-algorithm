package weekly.w384.A;

/**
 * 3033. Modify the Matrix
 *
 * https://leetcode.cn/contest/weekly-contest-384/problems/modify-the-matrix/
 *
 * Given a 0-indexed m x n integer matrix matrix, create a new 0-indexed matrix called answer.
 *
 * Make answer equal to matrix, then replace each element with the value -1 with the maximum
 * element in its respective column.
 *
 * Return the matrix answer.
 */

public class Solution {

    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] max = new int[n];

        for (int j = 0; j < n; j++) {
            max[j] = -100;
            for (int[] row : matrix) {
                max[j] = Math.max(max[j], row[j]);
            }
        }

        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0;j < n; j++) {
                ans[i][j] = matrix[i][j];
                if (ans[i][j] == -1) ans[i][j] = max[j];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
