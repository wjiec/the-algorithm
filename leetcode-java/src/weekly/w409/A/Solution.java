package weekly.w409.A;

/**
 * 3242. Design Neighbor Sum Service
 *
 * https://leetcode.cn/contest/weekly-contest-409/problems/design-neighbor-sum-service/
 *
 * You are given a n x n 2D array grid containing distinct elements in the range [0, n2 - 1].
 *
 * Implement the neighborSum class:
 *
 * neighborSum(int [][]grid) initializes the object.
 *
 * int adjacentSum(int value) returns the sum of elements which are adjacent
 * neighbors of value, that is either to the top, left, right, or bottom of value in grid.
 *
 * int diagonalSum(int value) returns the sum of elements which are diagonal
 * neighbors of value, that is either to the top-left, top-right, bottom-left,
 * or bottom-right of value in grid.
 */

public class Solution {

    private static class neighborSum {
        private final int n;
        private final int[][] pos, grid;
        public neighborSum(int[][] grid) {
            this.grid = grid; n = grid.length;
            pos = new int[n * n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    pos[grid[i][j]] = new int[]{i, j};
                }
            }
        }

        public int adjacentSum(int value) {
            int x = pos[value][0], y = pos[value][1];
            return at(x + 1, y) + at(x - 1, y) + at(x, y + 1) + at(x, y - 1);
        }

        public int diagonalSum(int value) {
            int x = pos[value][0], y = pos[value][1];
            return at(x + 1, y - 1) + at(x + 1, y + 1) + at(x - 1, y - 1) + at(x - 1, y + 1);
        }

        private int at(int x, int y) {
            return (x >= 0 && x < n && y >= 0 && y < n) ? grid[x][y] : 0;
        }
    }

    public static void main(String[] args) {
    }

}
