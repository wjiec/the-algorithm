package weekly.bw86.C;

/**
 * 6173. Maximum Rows Covered by Columns
 *
 * https://leetcode.cn/contest/biweekly-contest-86/problems/maximum-rows-covered-by-columns/
 *
 * You are given a 0-indexed m x n binary matrix mat and an integer cols, which
 * denotes the number of columns you must choose.
 *
 * A row is covered by a set of columns if each cell in the row that has a value of 1
 * also lies in one of the columns of the chosen set.
 *
 * Return the maximum number of rows that can be covered by a set of cols columns.
 */

public class Solution {

    public int maximumRows(int[][] mat, int cols) {
        int[] states = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            int state = 0, n = mat[i].length;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    state |= 1 << j;
                }
            }
            states[i] = state;
        }

        int n = 1 << (mat[0].length + 2), ans = 0;
        for (int i = 0; i < n; i++) {
            if (Integer.bitCount(i) == cols) {
                int curr = 0;
                for (var state : states) {
                    if ((state | i) == i) curr++;
                }
                ans = Math.max(ans, curr);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumRows(new int[][]{
            {1,0,0,0,0,0,0},
            {0,1,0,1,1,1,1},
            {0,0,0,1,0,0,1}
        }, 5) == 2;
    }

}
