package problem.p1039minimumscoretriangulationofpolygon;

import common.TODO;

/**
 * 1039. Minimum Score Triangulation of Polygon
 *
 * https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/
 *
 * You have a convex n-sided polygon where each vertex has an integer value. You are
 * given an integer array values where values[i] is the value of
 * the ith vertex (i.e., clockwise order).
 *
 * You will triangulate the polygon into n - 2 triangles. For each triangle, the
 * value of that triangle is the product of the values of its vertices, and
 * the total score of the triangulation is the sum of these values over
 * all n - 2 triangles in the triangulation.
 *
 * Return the smallest possible total score that you can achieve with
 * some triangulation of the polygon.
 */

public class Solution {

    @TODO
    public int minScoreTriangulation(int[] values) {
        int[][] dp = new int[values.length][values.length];
        for (int i = values.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < values.length; j++) {
                for (int k = i + 1; k < j; k++) {
                    if (dp[i][j] == 0)
                        dp[i][j] = values[i] * values[j] * values[k] + dp[i][k] + dp[k][j];
                    else
                        dp[i][j] = Math.min(dp[i][j], values[i] * values[j] * values[k] + dp[i][k] + dp[k][j]);
                }
            }
        }
        return dp[0][values.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().minScoreTriangulation(new int[]{1,2,3}) == 6;
        assert new Solution().minScoreTriangulation(new int[]{3,7,4,5}) == 144;
        assert new Solution().minScoreTriangulation(new int[]{1,3,1,4,1,5}) == 13;
    }

}
