package problem.p733floodfill;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 733. Flood Fill
 *
 * https://leetcode-cn.com/problems/flood-fill/
 *
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 *
 * You are also given three integers sr, sc, and newColor.
 * You should perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel,
 * plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
 * plus any pixels connected 4-directionally to those pixels (also with the same color), and so on.
 * Replace the color of all of the aforementioned pixels with newColor.
 *
 * Return the modified image after performing the flood fill.
 */

public class Solution {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int n = image.length, m = image[0].length, v = image[sr][sc];
        if (v == newColor) return image;

        Queue<int[]> queue = new LinkedList<>(); queue.add(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            var loc = queue.remove();
            image[loc[0]][loc[1]] = newColor;

            // left
            if (loc[0] > 0 && image[loc[0] - 1][loc[1]] == v) {
                queue.add(new int[]{loc[0] - 1, loc[1]});
            }
            // right
            if (loc[0] < n - 1 && image[loc[0] + 1][loc[1]] == v) {
                queue.add(new int[]{loc[0] + 1, loc[1]});
            }
            // top
            if (loc[1] > 0 && image[loc[0]][loc[1] - 1] == v) {
                queue.add(new int[]{loc[0], loc[1] - 1});
            }
            // bottom
            if (loc[1] < m - 1 && image[loc[0]][loc[1] + 1] == v) {
                queue.add(new int[]{loc[0], loc[1] + 1});
            }
        }

        return image;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().floodFill(new int[][]{{0,0,0}, {0,1,1}}, 1, 1, 1),
            new int[][]{{0,0,0}, {0,1,1}});
        assert Arrays.deepEquals(new Solution().floodFill(new int[][]{{1,1,1}, {1,1,0}, {1,0,1}}, 1, 1, 2),
            new int[][]{{2,2,2}, {2,2,0}, {2,0,1}});
    }

}
