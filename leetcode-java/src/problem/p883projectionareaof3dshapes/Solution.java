package problem.p883projectionareaof3dshapes;

/**
 * 883. Projection Area of 3D Shapes
 *
 * https://leetcode-cn.com/problems/projection-area-of-3d-shapes/
 *
 * You are given an n x n grid where we place some 1 x 1 x 1 cubes that are axis-aligned with the x, y, and z axes.
 *
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of the cell (i, j).
 *
 * We view the projection of these cubes onto the xy, yz, and zx planes.
 *
 * A projection is like a shadow, that maps our 3-dimensional figure to a 2-dimensional plane.
 * We are viewing the "shadow" when looking at the cubes from the top, the front, and the side.
 *
 * Return the total area of all three projections.
 */

public class Solution {

    // i != j & i == j
    public int projectionArea(int[][] grid) {
        int frontal = 0, side = 0, top = 0;
        for (var row : grid) {
            int highest = 0;
            for (var v : row) {
                highest = Math.max(highest, v);
                if (v != 0) top++;
            }
            frontal += highest;
        }

        for (int i = 0, n = grid[0].length; i < n; i++) {
            int highest = 0;
            for (int[] row : grid) {
                highest = Math.max(highest, row[i]);
            }
            side += highest;
        }

        return top + frontal + side;
    }

    public static void main(String[] args) {
        assert new Solution().projectionArea(new int[][]{{2}}) == 5;
        assert new Solution().projectionArea(new int[][]{{1,2},{3,4}}) == 17;
        assert new Solution().projectionArea(new int[][]{{1,2},{3,4}}) == 17;
        assert new Solution().projectionArea(new int[][]{{1,0},{0,2}}) == 8;
        assert new Solution().projectionArea(new int[][]{{1,1,1},{1,0,1},{1,1,1}}) == 14;
        assert new Solution().projectionArea(new int[][]{{1,1,1},{1,0,1},{1,1,1}}) == 14;
        assert new Solution().projectionArea(new int[][]{{2,2,2},{2,1,2},{2,2,2}}) == 21;
    }
}
