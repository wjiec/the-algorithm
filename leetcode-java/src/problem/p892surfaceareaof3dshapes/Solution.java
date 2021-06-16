package problem.p892surfaceareaof3dshapes;

import java.util.Map;

/**
 * 892. Surface Area of 3D Shapes
 *
 * https://leetcode-cn.com/problems/surface-area-of-3d-shapes/
 *
 * You are given an n x n grid where you have placed some 1 x 1 x 1 cubes.
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of cell (i, j).
 *
 * After placing these cubes, you have decided to glue any directly adjacent cubes to each other,
 * forming several irregular 3D shapes.
 *
 * Return the total surface area of the resulting shapes.
 *
 * Note: The bottom face of each shape counts toward its surface area.
 */

public class Solution {

    public int surfaceArea(int[][] grid) {
        int l = grid.length, total = 0, overlap = 0;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (grid[i][j] != 0) {
                    total += grid[i][j] * 6;
                    overlap += (grid[i][j] - 1) * 2; // vertical
                    if (i > 0) {
                        overlap += Math.min(grid[i][j], grid[i - 1][j]) * 2; // side
                    }
                    if (j > 0) {
                        overlap += Math.min(grid[i][j], grid[i][j - 1]) * 2; // frontal
                    }
                }
            }
        }
        return total - overlap;
    }

    public static void main(String[] args) {
        assert new Solution().surfaceArea(new int[][]{{2}}) == 10;
        assert new Solution().surfaceArea(new int[][]{{1,2}, {3,4}}) == 34;
        assert new Solution().surfaceArea(new int[][]{{1,0}, {0,2}}) == 16;
        assert new Solution().surfaceArea(new int[][]{{1,1,1}, {1,0,1}, {1,1,1}}) == 32;
        assert new Solution().surfaceArea(new int[][]{{2,2,2}, {2,1,2}, {2,2,2}}) == 46;
    }

}
