package daily.d211213p807maxincreasetokeepcityskyline;

import java.util.Arrays;

/**
 * 807. Max Increase to Keep City Skyline
 *
 * https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/
 *
 * There is a city composed of n x n blocks, where each block contains a single
 * building shaped like a vertical square prism.
 *
 * You are given a 0-indexed n x n integer matrix grid where grid[r][c] represents
 * the height of the building located in the block at row r and column c.
 *
 * A city's skyline is the the outer contour formed by all the building when viewing
 * the side of the city from a distance.
 *
 * The skyline from each cardinal direction north, east, south, and west may be different.
 *
 * We are allowed to increase the height of any number of buildings by
 * any amount (the amount can be different per building).
 *
 * The height of a 0-height building can also be increased.
 * However, increasing the height of a building should not affect
 * the city's skyline from any cardinal direction.
 *
 * Return the maximum total sum that the height of the buildings can be
 * increased by without changing the city's skyline from any cardinal direction.
 */

public class Solution {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] horizontal = new int[grid.length], vertical = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                horizontal[i] = Math.max(horizontal[i], grid[j][i]);
                vertical[i] = Math.max(vertical[i], grid[i][j]);
            }
        }

        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                ans += Math.min(horizontal[i], vertical[j]) - grid[i][j];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxIncreaseKeepingSkyline(new int[][]{
            {3,0,8,4},
            {2,4,5,7},
            {9,2,6,3},
            {0,3,1,0}
        }) == 35;

        assert new Solution().maxIncreaseKeepingSkyline(new int[][]{
            {0,0,0},
            {0,0,0},
            {0,0,0},
        }) == 0;
    }

}
