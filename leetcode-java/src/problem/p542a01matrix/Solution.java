package problem.p542a01matrix;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 542. 01 Matrix
 *
 * https://leetcode-cn.com/problems/01-matrix/
 *
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 */

public class Solution {

    private final int[][] dirs = new int[][]{
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;

        int[][] ans = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1], v = curr[2];
            for (var dir : dirs) {
                int a = x + dir[0], b = y + dir[1];
                if (a >= 0 && a < m && b >= 0 && b < n && !visited[a][b]) {
                    visited[a][b] = true;
                    ans[a][b] = v + 1;
                    queue.add(new int[]{a, b, v + 1});
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
