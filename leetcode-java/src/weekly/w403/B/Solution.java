package weekly.w403.B;

/**
 * 3195. Find the Minimum Area to Cover All Ones I
 *
 * https://leetcode.cn/contest/weekly-contest-403/problems/find-the-minimum-area-to-cover-all-ones-i/
 *
 * You are given a 2D binary array grid. Find a rectangle with horizontal and vertical sides
 * with the smallest area, such that all the 1's in grid lie inside this rectangle.
 *
 * Return the minimum possible area of the rectangle.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minimumArea(int[][] grid) {
        int miX = 1001, miY = 1001, mxX = 0, mxY = 0, c = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    c++;
                    miX = Math.min(miX, i);
                    miY = Math.min(miY, j);
                    mxX = Math.max(mxX, i);
                    mxY = Math.max(mxY, j);
                }
            }
        }
        if (c == 0) return 0;

        return (mxX - miX + 1) * (mxY - miY + 1);
    }

    public static void main(String[] args) {
    }

}
