package problem.p1559detectcyclesin2dgrid;

import common.TODO;

/**
 * 1559. Detect Cycles in 2D Grid
 *
 * https://leetcode.cn/problems/detect-cycles-in-2d-grid/
 *
 * Given a 2D array of characters grid of size m x n, you need to find if there
 * exists any cycle consisting of the same value in grid.
 *
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell.
 * From a given cell, you can move to one of the cells adjacent to it - in one of the four
 * directions (up, down, left, or right), if it has the same value of the current cell.
 *
 * Also, you cannot move to the cell that you visited in your last move.
 * For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2)
 * we visited (1, 1) which was the last visited cell.
 *
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n + 1];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int v) {
            return v == parent[v] ? v : (parent[v] = find(parent[v]));
        }
        public boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return false;

            if (rank[px] < rank[py]) parent[px] = py;
            else parent[py] = px;

            if (rank[px] == rank[py]) rank[py]++;
            return true;
        }
    }

    @TODO(tips = "UnionFind")
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != m - 1 && grid[i][j] == grid[i + 1][j]) {
                    if (!uf.union(i * n + j, (i + 1) * n + j)) {
                        return true;
                    }
                }
                if (j != n - 1 && grid[i][j] == grid[i][j + 1]) {
                    if (!uf.union(i * n + j, i * n + j + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().containsCycle(new char[][]{
            {'b'},
            {'b'}
        });

        assert new Solution().containsCycle(new char[][]{
            {'a','a','a','a'},
            {'a','b','b','a'},
            {'a','b','b','a'},
            {'a','a','a','a'}
        });

        assert new Solution().containsCycle(new char[][]{
            {'c','c','c','a'},
            {'c','d','c','c'},
            {'c','c','e','c'},
            {'f','c','c','c'}
        });

        assert !new Solution().containsCycle(new char[][]{
            {'a','b','b'},
            {'b','z','b'},
            {'b','b','a'}
        });
    }

}
