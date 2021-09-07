package problem.p1351countnegativenumbersinasortedmatrix;

/**
 * 1351. Count Negative Numbers in a Sorted Matrix
 *
 * https://leetcode-cn.com/problems/count-negative-numbers-in-a-sorted-matrix/
 *
 * Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise,
 * return the number of negative numbers in grid.
 */

public class Solution {

    public int countNegatives(int[][] grid) {
        int ans = 0, row = 0, col = grid[0].length - 1;
        while (row < grid.length && col >= 0) {
            if (grid[row][col] >= 0) row++;
            else {
                ans += grid.length - row;
                col--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countNegatives(new int[][]{
            {4,3,2,-1},
            {3,2,1,-1},
            {1,1,-1,-2},
            {-1,-1,-2,-3}
        }) == 8;

        assert new Solution().countNegatives(new int[][]{
            {3,2},
            {1,0}
        }) == 0;

        assert new Solution().countNegatives(new int[][]{
            {1,-1},
            {-1,-1}
        }) == 3;

        assert new Solution().countNegatives(new int[][]{
            {-1}
        }) == 1;
    }

}
