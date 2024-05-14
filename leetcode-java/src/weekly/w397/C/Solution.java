package weekly.w397.C;

import java.util.Arrays;
import java.util.List;

/**
 * 3148. Maximum Difference Score in a Grid
 *
 * https://leetcode.cn/contest/weekly-contest-397/problems/maximum-difference-score-in-a-grid/
 *
 * You are given an m x n matrix grid consisting of positive integers.
 * You can move from a cell in the matrix to any other cell that is either
 * to the bottom or to the right (not necessarily adjacent).
 *
 * The score of a move from a cell with the value c1 to a cell with the value c2 is c2 - c1.
 *
 * You can start at any cell, and you have to make at least one move.
 *
 * Return the maximum total score you can achieve.
 */

public class Solution {

    public int maxScore(List<List<Integer>> grid) {
        int n = grid.get(0).size();

        // 每一列的最小值
        int[] colMin = new int[n];
        Arrays.fill(colMin, Integer.MAX_VALUE);

        int ans = Integer.MIN_VALUE;
        for (var row : grid) {
            // 从左边转移的最小值
            int leftMin = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                int v = row.get(j);
                // 从左边或者左上角转移的最大值
                ans = Math.max(ans, v - Math.min(colMin[j], leftMin));
                // 维护每一列的最小值
                colMin[j] = Math.min(colMin[j], v);
                // 维护当前左边可选的最小值
                leftMin = Math.min(leftMin, colMin[j]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(List.of(
            List.of(9,5,7,3),
            List.of(8,9,6,1),
            List.of(6,7,14,3),
            List.of(2,5,3,1)
        )) == 9;

        assert new Solution().maxScore(List.of(
            List.of(4,3,2),
            List.of(3,2,1)
        )) == -1;
    }

}
