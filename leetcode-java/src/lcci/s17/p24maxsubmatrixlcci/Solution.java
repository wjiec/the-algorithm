package lcci.s17.p24maxsubmatrixlcci;

import common.Checker;

import java.util.Arrays;

/**
 * 面试题 17.24. 最大子矩阵
 *
 * https://leetcode.cn/problems/max-submatrix-lcci/
 *
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 *
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，
 * r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 */

public class Solution {

    public int[] getMaxMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int r = 0, c = 0;
        int max = Integer.MIN_VALUE;
        int[] ans = new int[]{0, 0, 0, 0};

        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp, 0);

            for (int j = i; j < m; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    dp[k] += matrix[j][k];
                    if (sum > 0) sum += dp[k];
                    else { sum = dp[k]; r = i; c = k; }

                    if (sum > max) {
                        max = sum;
                        ans[0] = r; ans[1] = c;
                        ans[2] = j; ans[3] = k;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getMaxMatrix(new int[][]{
            {-1,0},
            {0,-1}
        }), new int[]{0,1,0,1});
    }

}
