package offer2.p40;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer II 040. 矩阵中最大的矩形
 *
 * https://leetcode.cn/problems/PLYXKQ
 *
 * 给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 注意：此题 matrix 输入格式为一维 01 字符串数组。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maximalRectangle(String[] matrix) {
        if (matrix.length == 0) return 0;

        char[][] cMatrix = new char[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            cMatrix[i] = matrix[i].toCharArray();
        }

        int m = cMatrix.length, n = cMatrix[0].length;
        int[][] leftOne = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cMatrix[i][j] == '1') {
                    if (j == 0) leftOne[i][j] = 1;
                    else leftOne[i][j] = 1 + leftOne[i][j - 1];
                }
            }
        }

        int ans = 0;
        for (int j = 0; j < n; j++) {
            Deque<Integer> stack = new ArrayDeque<>();

            // down[i] 表示 往下找 第一个小于当前值的位置
            int[] down = new int[m];
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && leftOne[i][j] < leftOne[stack.peek()][j]) {
                    down[stack.pop()] = i;
                }
                stack.push(i);
            }
            while (!stack.isEmpty()) down[stack.pop()] = m;

            // up[i] 表示 往上找 第一个小于当前值的位置
            int[] up = new int[m];
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && leftOne[i][j] < leftOne[stack.peek()][j]) {
                    up[stack.pop()] = i;
                }
                stack.push(i);
            }
            while (!stack.isEmpty()) up[stack.pop()] = -1;

            for (int i = 0; i < m; i++) {
                ans = Math.max(ans, leftOne[i][j] * (down[i] - up[i] - 1));
            }
        }

        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximalRectangle(new String[]{"01", "01"}) == 2;

        assert new Solution().maximalRectangle(new String[]{"10100","10111","11111","10010"}) == 6;
        assert new Solution().maximalRectangle(new String[]{}) == 0;
        assert new Solution().maximalRectangle(new String[]{"0"}) == 0;
        assert new Solution().maximalRectangle(new String[]{"1"}) == 1;
        assert new Solution().maximalRectangle(new String[]{"00"}) == 0;
    }

}
