package problem.p85maximalrectangle;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 85. Maximal Rectangle
 *
 * https://leetcode.cn/problems/maximal-rectangle/
 *
 * Given a rows x cols binary matrix filled with 0's and 1's, find
 * the largest rectangle containing only 1's and return its area.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = 1 + (j != 0 ? left[i][j - 1] : 0);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = left[i][j];
                for (int k = i; k >= 0; k--) {
                    len = Math.min(len, left[k][j]);
                    ans = Math.max(ans, len * (i - k + 1));
                }
            }
        }

        return ans;
    }

    private static class MoonStack {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length, n = matrix[0].length;

            int[][] left = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        left[i][j] = 1 + (j != 0 ? left[i][j - 1] : 0);
                    }
                }
            }

            int ans = 0;
            // 计算每一列中的柱状图
            for (int j = 0; j < n; j++) {
                int[] up = new int[m];
                Deque<Integer> stack = new ArrayDeque<>();
                for (int i = 0; i < m; i++) {
                    while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                        stack.pop();
                    }

                    up[i] = stack.isEmpty() ? -1 : stack.peek();
                    stack.push(i);
                }

                int[] down = new int[m]; stack.clear();
                for (int i = m - 1; i >= 0; i--) {
                    while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                        stack.pop();
                    }

                    down[i] = stack.isEmpty() ? m : stack.peek();
                    stack.push(i);
                }

                for (int i = 0; i < m; i++) {
                    int h = down[i] - up[i] - 1;
                    ans = Math.max(ans, h * left[i][j]);
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maximalRectangle(new char[][]{
            {'0','0','1','0'},
            {'0','0','1','0'},
            {'0','0','1','0'},
            {'0','0','1','1'},
            {'0','1','1','1'},
            {'0','1','1','1'},
            {'1','1','1','1'}
        }) == 9;

        assert new MoonStack().maximalRectangle(new char[][]{
            {'0','0','1','0'},
            {'0','0','1','0'},
            {'0','0','1','0'},
            {'0','0','1','1'},
            {'0','1','1','1'},
            {'0','1','1','1'},
            {'1','1','1','1'}
        }) == 9;

        assert new Solution().maximalRectangle(new char[][]{
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'},
        }) == 6;

        assert new Solution().maximalRectangle(new char[][]{
            {},
        }) == 0;

        assert new Solution().maximalRectangle(new char[][]{
            {'0'},
        }) == 0;

        assert new Solution().maximalRectangle(new char[][]{
            {'1'},
        }) == 1;

        assert new Solution().maximalRectangle(new char[][]{
            {'0', '0'},
        }) == 0;
    }

}
