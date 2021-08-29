package problem.p1260shift2dgrid;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 1260. Shift 2D Grid
 *
 * https://leetcode-cn.com/problems/shift-2d-grid/
 *
 * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
 *
 * In one shift operation:
 *
 * Element at grid[i][j] moves to grid[i][j + 1].
 * Element at grid[i][n - 1] moves to grid[i + 1][0].
 * Element at grid[m - 1][n - 1] moves to grid[0][0].
 *
 * Return the 2D grid after applying shift operation k times.
 */

public class Solution {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        int m = grid.length, n = grid[0].length, t = m * n, r = t - (k % t);
        for (int i = 0, c = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++, c++) {
                int v = (c + r) % t, x = v / n, y = v % n;
                row.add(grid[x][y]);
            }
            ans.add(row);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shiftGrid(new int[][]{
            {1},
            {2},
            {3},
            {4},
            {7},
            {6},
            {5},
        }, 23));

        System.out.println(new Solution().shiftGrid(new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        }, 1));
        System.out.println(new Solution().shiftGrid(new int[][]{
            {3,8,1,9},
            {19,7,2,5},
            {4,6,11,10},
            {12,0,21,13}
        }, 4));
        System.out.println(new Solution().shiftGrid(new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        }, 9));
    }

}
