package weekly.w452.B;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q2. Minimum Absolute Difference in Sliding Submatrix
 *
 * https://leetcode.cn/contest/weekly-contest-452/problems/minimum-absolute-difference-in-sliding-submatrix
 *
 * You are given an m x n integer matrix grid and an integer k.
 *
 * For every contiguous k x k submatrix of grid, compute the minimum absolute difference
 * between any two distinct values within that submatrix.
 *
 * Return a 2D array ans of size (m - k + 1) x (n - k + 1), where ans[i][j] is the minimum absolute
 * difference in the submatrix whose top-left corner is (i, j) in grid.
 *
 * Note: If all elements in the submatrix have the same value, the answer will be 0.
 *
 * A submatrix (x1, y1, x2, y2) is a matrix that is formed by choosing all cells matrix[x][y]
 * where x1 <= x <= x2 and y1 <= y <= y2.
 */

public class Solution {

    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        if (k == 1) return ans;

        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                ans[i][j] = minAbsDiff(grid, i, j, i + k - 1, j + k - 1);
            }
        }
        return ans;
    }

    private int minAbsDiff(int[][] grid, int x1, int y1, int x2, int y2) {
        Set<Integer> s = new HashSet<>();
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                s.add(grid[i][j]);
            }
        }

        List<Integer> x = new ArrayList<>(s);
        if (x.size() == 1) return 0;
        x.sort(Integer::compare);

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < x.size(); i++) {
            ans = Math.min(ans, x.get(i) - x.get(i - 1));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
