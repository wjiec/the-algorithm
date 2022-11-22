package offer2.p107;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 剑指 Offer II 107. 矩阵中的距离
 *
 * https://leetcode.cn/problems/2bCMpM/
 *
 * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(ans[i], Integer.MAX_VALUE);

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    ans[i][j] = 0;
                    queue.add(new int[]{i, j, 0});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && ans[dx][dy] == Integer.MAX_VALUE) {
                    ans[dx][dy] = curr[2] + 1;
                    queue.add(new int[]{dx, dy, curr[2] + 1});
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().updateMatrix(new int[][]{{0,0,0},{0,1,0},{0,0,0}}), new int[][]{{0,0,0},{0,1,0},{0,0,0}});
        assert Checker.check(new Solution().updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}}), new int[][]{{0,0,0},{0,1,0},{1,2,1}});
    }

}
