package problem.p2087minimumcosthomecomingofarobotinagrid;

/**
 * 2087. Minimum Cost Homecoming of a Robot in a Grid
 *
 * https://leetcode.cn/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/
 *
 * There is an m x n grid, where (0, 0) is the top-left cell and (m - 1, n - 1) is the bottom-right cell.
 * You are given an integer array startPos where startPos = [startrow, startcol] indicates that initially, a
 * robot is at the cell (startrow, startcol). You are also given an integer array homePos where
 * homePos = [homerow, homecol] indicates that its home is at the cell (homerow, homecol).
 *
 * The robot needs to go to its home. It can move one cell in four directions: left, right, up, or down, and
 * it can not move outside the boundary. Every move incurs some cost. You are further given two 0-indexed
 * integer arrays: rowCosts of length m and colCosts of length n.
 *
 * If the robot moves up or down into a cell whose row is r, then this move costs rowCosts[r].
 * If the robot moves left or right into a cell whose column is c, then this move costs colCosts[c].
 * Return the minimum total cost for this robot to return home.
 */

public class Solution {

    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int sx = startPos[0], sy = startPos[1], hx = homePos[0], hy = homePos[1];

        int ans = 0, x1 = Math.min(sx, hx), x2 = Math.max(sx, hx);
        for (int i = x1; i <= x2; i++) ans += rowCosts[i];

        int y1 = Math.min(sy, hy), y2 = Math.max(sy, hy);
        for (int i = y1; i <= y2; i++) ans += colCosts[i];

        ans -= rowCosts[sx] + colCosts[sy];
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minCost(new int[]{1, 0}, new int[]{2, 3}, new int[]{5, 4, 3}, new int[]{8, 2, 6, 7}) == 18;
        assert new Solution().minCost(new int[]{0, 0}, new int[]{0, 0}, new int[]{5}, new int[]{26}) == 0;
    }

}
