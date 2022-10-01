package problem.p1981minimizethedifferencebetweentargetandchosenelements;

import java.util.Arrays;

/**
 * 1981. Minimize the Difference Between Target and Chosen Elements
 *
 * https://leetcode.cn/problems/minimize-the-difference-between-target-and-chosen-elements/
 *
 * You are given an m x n integer matrix mat and an integer target.
 *
 * Choose one integer from each row in the matrix such that the absolute difference
 * between target and the sum of the chosen elements is minimized.
 *
 * Return the minimum absolute difference.
 *
 * The absolute difference between two numbers a and b is the absolute value of a - b.
 */

public class Solution {

    private int n = 0, t = 0;
    private int ans = Integer.MAX_VALUE;

    public int minimizeTheDifference(int[][] mat, int target) {
        n = mat[0].length; t = target;
        for (var row : mat) Arrays.sort(row);
        backtrack(mat, 0, 0);
        return ans;
    }

    private final boolean[][] memo = new boolean[71][5000];

    private void backtrack(int[][] matrix, int idx, int sum) {
        if (idx == matrix.length) {
            ans = Math.min(ans, Math.abs(t - sum));
            return;
        }

        if (memo[idx][sum]) return;
        memo[idx][sum] = true;

        for (int i = 0; i < n; i++) {
            int curr = sum + matrix[idx][i];
            if (curr - t > ans) break;
            backtrack(matrix, idx + 1, curr);
        }
    }

    public static void main(String[] args) {
        assert new Solution().minimizeTheDifference(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, 13) == 0;
        assert new Solution().minimizeTheDifference(new int[][]{{1},{2},{3}}, 100) == 94;
        assert new Solution().minimizeTheDifference(new int[][]{{1,2,9,8,7}}, 6) == 1;
    }

}
