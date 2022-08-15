package problem.p1504countsubmatriceswithallones;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1504. Count Submatrices With All Ones
 *
 * https://leetcode.cn/problems/count-submatrices-with-all-ones/
 *
 * Given an m x n binary matrix mat, return the number of submatrices that have all ones.
 */

public class Solution {

    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] rows = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) rows[i][j] = mat[i][j];
                else if (mat[i][j] == 0) rows[i][j] = 0;
                else rows[i][j] = rows[i][j - 1] + 1;
            }
        }

        int ans = 0;
        for (int j = 0; j < n; j++) {
            int sum = 0;
            Deque<int[]> stack = new ArrayDeque<>();
            for (int i = 0; i < m; i++) {
                int height = 1;
                while (!stack.isEmpty() && stack.peek()[0] > rows[i][j]) {
                    sum -= stack.peek()[1] * (stack.peek()[0] - rows[i][j]);
                    height += stack.pop()[1];
                }

                sum += rows[i][j]; ans += sum;
                stack.push(new int[]{rows[i][j], height});
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSubmat(new int[][]{{1,0,1},{1,1,0},{1,1,0}}) == 13;
        assert new Solution().numSubmat(new int[][]{{0,1,1,0},{0,1,1,1},{1,1,1,0}}) == 24;
    }

}
