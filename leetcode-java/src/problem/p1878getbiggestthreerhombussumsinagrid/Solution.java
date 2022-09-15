package problem.p1878getbiggestthreerhombussumsinagrid;

import common.Checker;
import common.TODO;
import common.Tag;

import java.util.TreeSet;

/**
 * 1878. Get Biggest Three Rhombus Sums in a Grid
 *
 * https://leetcode.cn/problems/get-biggest-three-rhombus-sums-in-a-grid/
 *
 * You are given an m x n integer matrix grid.
 *
 * A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid.
 * The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell.
 * Below is an image of four valid rhombus shapes with the corresponding colored cells that
 * should be included in each rhombus sum:
 *
 * Note that the rhombus can have an area of 0, which is depicted
 * by the purple rhombus in the bottom right corner.
 *
 * Return the biggest three distinct rhombus sums in the grid in descending order.
 * If there are less than three distinct values, return all of them.
 */

public class Solution {

    @TODO(tips = "前缀和")
    @Tag({"数组菱形", "菱形", "最大菱形"})
    public int[] getBiggestThree(int[][] grid) {
        int[][] s1 = new int[101][101];
        int[][] s2 = new int[101][101];

        int m = grid.length, n = grid[0].length;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s1[i][j] = s1[i - 1][j - 1] + grid[i - 1][j - 1];
                s2[i][j] = s2[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                set.add(grid[i - 1][j - 1]);
                for (int k = 1; i - k >= 1 && i + k <= m && j - k >= 1 && j + k <= n; k++) {
                    int a = s2[i][j - k] - s2[i - k][j];
                    int b = s1[i][j + k] - s1[i - k][j];
                    int c = s2[i + k][j] - s2[i][j + k];
                    int d = s1[i + k][j] - s1[i][j - k];
                    set.add(a + b + c + d - grid[i + k - 1][j - 1] + grid[i - k - 1][j - 1]);
                }
                while (set.size() > 3) set.pollFirst();
            }
        }

        if (set.size() == 1) return new int[]{set.pollLast()};
        if (set.size() == 2) return new int[]{set.pollLast(), set.pollLast()};
        return new int[]{set.pollLast(), set.pollLast(), set.pollLast()};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getBiggestThree(new int[][]{
            {3,4,5,1,3},
            {3,3,4,2,3},
            {20,30,200,40,10},
            {1,5,5,4,1},
            {4,3,2,2,5},
        }), new int[]{228,216,211});

        assert Checker.check(new Solution().getBiggestThree(new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        }), new int[]{20,9,8});

        assert Checker.check(new Solution().getBiggestThree(new int[][]{
            {7,7,7},
        }), new int[]{7});
    }

}
