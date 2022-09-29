package problem.p1975maximummatrixsum;

/**
 * 1975. Maximum Matrix Sum
 *
 * https://leetcode.cn/problems/maximum-matrix-sum/
 *
 * You are given an n x n integer matrix. You can do the following operation any number of times:
 *
 * Choose any two adjacent elements of matrix and multiply each of them by -1.
 * Two elements are considered adjacent if and only if they share a border.
 *
 * Your goal is to maximize the summation of the matrix's elements.
 * Return the maximum sum of the matrix's elements using the operation mentioned above.
 */

public class Solution {

    public long maxMatrixSum(int[][] matrix) {
        long ans = 0, count = 0, max = Long.MAX_VALUE;
        for (int[] row : matrix) {
            for (int val : row) {
                if (val <= 0) count++;
                ans += Math.abs(val);
                max = Math.min(max, Math.abs(val));
            }
        }
        return (count & 1) == 0 ? ans : ans - 2 * max;
    }

    public static void main(String[] args) {
        assert new Solution().maxMatrixSum(new int[][]{{1,-1},{-1,1}}) == 4;
        assert new Solution().maxMatrixSum(new int[][]{{1,2,3},{-1,-2,-3},{1,2,3}}) == 16;
    }

}
