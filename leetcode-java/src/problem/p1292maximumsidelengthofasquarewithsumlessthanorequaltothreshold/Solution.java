package problem.p1292maximumsidelengthofasquarewithsumlessthanorequaltothreshold;

/**
 * 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
 *
 * https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
 *
 * Given a m x n matrix mat and an integer threshold, return the maximum side-length of a square with
 * a sum less than or equal to threshold or return 0 if there is no such square.
 */

public class Solution {

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int ans = 0, r = Math.max(m, n);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = ans + 1; k <= r; k++) {
                    if (i + k - 1 <= m && j + k - 1 <= n && (
                        sum[i + k - 1][j + k - 1] - sum[i + k - 1][j - 1]
                            - sum[i - 1][j + k - 1] + sum[i - 1][j - 1]
                    ) <= threshold) ans = k;
                    else break;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxSideLength(new int[][]{
            {1,1,3,2,4,3,2},
            {1,1,3,2,4,3,2},
            {1,1,3,2,4,3,2}
        }, 4) == 2;

        assert new Solution().maxSideLength(new int[][]{
            {2,2,2,2,2},
            {2,2,2,2,2},
            {2,2,2,2,2},
            {2,2,2,2,2},
            {2,2,2,2,2}
        }, 1) == 0;
    }

}
