package problem.p1970lastdaywhereyoucanstillcross;

/**
 * 1970. Last Day Where You Can Still Cross
 *
 * https://leetcode.cn/problems/last-day-where-you-can-still-cross/
 *
 * There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given
 * integers row and col representing the number of rows and columns in the matrix, respectively.
 *
 * Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water.
 * You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the
 * cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).
 *
 * You want to find the last day that it is possible to walk from the top to the bottom by only walking
 * on land cells. You can start from any cell in the top row and end at any cell in the bottom row. You
 * can only travel in the four cardinal directions (left, right, up, and down).
 *
 * Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.
 */

public class Solution {

    private static class UnionFind {
        private final int m, n;
        private final int[] parent;
        public UnionFind(int m, int n) {
            this.m = m; this.n = n;
            parent = new int[m * n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public void union(int x1, int y1, int x2, int y2) {
            union(x2 * n + y2, x1 * n + y1);
        }

        // 可以通过增加一个 s, t 的守卫实现优化，当加入并查集的点为(0, y)时, 将 s 加入并查集
        // 当加入并查集的点为(m - 1, y)时, 将 t 加入并查集, 此时只需要检查 s, t 是否相连即可
        public boolean isOk() {
            for (int j = 0; j < n; j++) {
                if (find((m - 1) * n + j) < n) return true;
            }
            return false;
        }

        private int find(int v) { return parent[v] == v ? v : (parent[v] = find(parent[v])); }
        private void union(int a, int b) {
            int fa = find(a), fb = find(b);
            parent[fa] = parent[fb] = Math.min(fa, fb);
        }
    }

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int latestDayToCross(int row, int col, int[][] cells) {
        boolean[][] water = new boolean[row][col];
        for (var cell : cells) water[cell[0] - 1][cell[1] - 1] = true;

        UnionFind uf = new UnionFind(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!water[i][j]) {
                    if (i - 1 >= 0 && !water[i - 1][j]) uf.union(i, j, i - 1, j);
                    if (j - 1 >= 0 && !water[i][j - 1]) uf.union(i, j, i, j - 1);
                }
            }
        }
        if (uf.isOk()) return cells.length;

        for (int i = cells.length - 1; i >= 0; i--) {
            int x = cells[i][0] - 1, y = cells[i][1] - 1;
            water[x][y] = false;

            for (var dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx >= 0 && dx < row && dy >= 0 && dy < col && !water[dx][dy]) uf.union(x, y, dx, dy);
            }
            if (uf.isOk()) return i;
        }
        return -1; // unreached
    }

    public static void main(String[] args) {
        assert new Solution().latestDayToCross(2, 2, new int[][]{{1,1},{2,1},{1,2},{2,2}}) == 2;
        assert new Solution().latestDayToCross(2, 2, new int[][]{{1,1},{1,2},{2,1},{2,2}}) == 1;
        assert new Solution().latestDayToCross(3, 3, new int[][]{{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}}) == 3;
    }

}
