package weekly.bw129.B;

/**
 * 100278. Right Triangles
 *
 * https://leetcode.cn/contest/biweekly-contest-129/problems/right-triangles/
 *
 * You are given a 2D boolean matrix grid.
 *
 * Return an integer that is the number of right triangles that can be made with
 * the 3 elements of grid such that all of them have a value of 1.
 *
 * Note:
 *
 * A collection of 3 elements of grid is a right triangle if one of its elements is in
 * the same row with another element and in the same column with the third element.
 *
 * The 3 elements do not have to be next to each other.
 */

public class Solution {

    public long numberOfRightTriangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rows = new int[m], cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
            }
        }

        long ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ans += (rows[i] - 1L) * (cols[j] - 1L);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
