package problem.p1284minimumnumberofflipstoconvertbinarymatrixtozeromatrix;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
 *
 * https://leetcode.cn/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/
 *
 * Given a m x n binary matrix mat. In one step, you can choose one cell and flip
 * it and all the four neighbors of it if they exist (Flip is changing 1 to 0 and 0 to 1).
 *
 * A pair of cells are called neighbors if they share one edge.
 *
 * Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
 *
 * A binary matrix is a matrix with all cells equal to 0 or 1 only.
 *
 * A zero matrix is a matrix with all cells equal to 0.
 */

@SuppressWarnings("ManualArrayCopy")
public class Solution {

    private int m = 0, n = 0;
    private record State(int[][] matrix, int step, int hash) {}

    public int minFlips(int[][] mat) {
        m = mat.length; n = mat[0].length;

        boolean[] visited = new boolean[1 << (m * n)];
        Queue<State> queue = new ArrayDeque<>();

        State init = new State(mat, 0, hash(mat));
        queue.add(init); visited[init.hash] = true;

        while (!queue.isEmpty()) {
            State curr = queue.remove();
            if (curr.hash == 0) return curr.step;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int[][] next = flip(curr.matrix, i, j);
                    int nextHash = hash(next);
                    if (!visited[nextHash]) {
                        visited[nextHash] = true;
                        queue.add(new State(next, curr.step + 1, nextHash));
                    }
                }
            }
        }

        return -1;
    }

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int[][] flip(int[][] matrix, int x, int y) {
        int[][] next = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = matrix[i][j];
            }
        }

        next[x][y] ^= 1;
        for (var dir : dirs) {
            int dx = x + dir[0], dy = y + dir[1];
            if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                next[dx][dy] ^= 1;
            }
        }

        return next;
    }

    private int hash(int[][] matrix) {
        int v = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                v |= matrix[i][j] << (i * n + j);
            }
        }
        return v;
    }

    public static void main(String[] args) {
        assert new Solution().minFlips(new int[][]{{0},{1},{1}}) == 1;

        assert new Solution().minFlips(new int[][]{{0,0},{0,1}}) == 3;
        assert new Solution().minFlips(new int[][]{{0}}) == 0;
        assert new Solution().minFlips(new int[][]{{1,0,0},{1,0,0}}) == -1;
    }

}
