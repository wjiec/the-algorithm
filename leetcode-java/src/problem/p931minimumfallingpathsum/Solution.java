package problem.p931minimumfallingpathsum;

/**
 * 931. Minimum Falling Path Sum
 *
 * https://leetcode.cn/problems/minimum-falling-path-sum/
 *
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row
 * that is either directly below or diagonally left/right. Specifically, the next element
 * from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 */

public class Solution {

    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i - 1][j];
                if (j != 0) val = Math.min(val, matrix[i - 1][j - 1]);
                if (j != n - 1) val = Math.min(val, matrix[i - 1][j + 1]);
                matrix[i][j] += val;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) ans = Math.min(ans, matrix[m - 1][i]);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}}) == 13;
        assert new Solution().minFallingPathSum(new int[][]{{-19,57},{-40,-5}}) == -59;
    }

}
