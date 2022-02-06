package offer.p29shunshizhendayinjuzhenlcof;

import common.Checker;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 *
 * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */

public class Solution {

    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new int[]{};

        int n = matrix[0].length, idx = 0, max = m * n;
        int[] ans = new int[m * n];

        int t = 0, r = n, b = m, l = 0;
        while (t < b && l < r) {
            // top
            for (int i = l; i < r && idx < max; i++) {
                ans[idx++] = matrix[t][i];
            }
            t += 1;

            // right
            for (int i = t; i < b && idx < max; i++) {
                ans[idx++] = matrix[i][r - 1];
            }
            r -= 1;

            // bottom
            for (int i = r - 1; i >= l && idx < max; i--) {
                ans[idx++] = matrix[b - 1][i];
            }
            b -= 1;

            // left
            for (int i = b - 1; i >= t && idx < max; i--) {
                ans[idx++] = matrix[i][l];
            }
            l++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().spiralOrder(new int[][]{
            {1,2,3}, {4,5,6}, {7,8,9}
        }), new int[]{1,2,3,6,9,8,7,4,5});
        assert Checker.check(new Solution().spiralOrder(new int[][]{
            {1,2,3,4}, {5,6,7,8}, {9,10,11,12}
        }), new int[]{1,2,3,4,8,12,11,10,9,5,6,7});
    }

}
