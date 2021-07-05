package problem.p54spiralmatrix;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 *
 * https://leetcode-cn.com/problems/spiral-matrix/
 *
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */

public class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return List.of();

        List<Integer> ans = new ArrayList<>();
        int n = matrix[0].length, idx = 0, max = m * n;
        int t = 0, r = n, b = m, l = 0;
        while (t < b && l < r) {
            // top
            for (int i = l; i < r && idx < max; i++, idx++) {
                ans.add(matrix[t][i]);
            }
            t += 1;

            // right
            for (int i = t; i < b && idx < max; i++, idx++) {
                ans.add(matrix[i][r - 1]);
            }
            r -= 1;

            // bottom
            for (int i = r - 1; i >= l && idx < max; i--, idx++) {
                ans.add(matrix[b - 1][i]);
            }
            b -= 1;

            // left
            for (int i = b - 1; i >= t && idx < max; i--, idx++) {
                ans.add(matrix[i][l]);
            }
            l++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().spiralOrder(new int[][]{
            {1,2,3}, {4,5,6}, {7,8,9}
        }).equals(List.of(1,2,3,6,9,8,7,4,5));
        assert new Solution().spiralOrder(new int[][]{
            {1,2,3,4}, {5,6,7,8}, {9,10,11,12}
        }).equals(List.of(1,2,3,4,8,12,11,10,9,5,6,7));
    }

}
