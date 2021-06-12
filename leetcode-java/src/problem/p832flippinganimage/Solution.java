package problem.p832flippinganimage;

import java.util.Arrays;

/**
 * 832. Flipping an Image
 *
 * https://leetcode-cn.com/problems/flipping-an-image/
 *
 * Given an n x n binary matrix image, flip the image horizontally, then invert it, and return the resulting image.
 *
 * To flip an image horizontally means that each row of the image is reversed.
 *
 * For example, flipping [1,1,0] horizontally results in [0,1,1].
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
 *
 * For example, inverting [0,1,1] results in [1,0,0].
 */

public class Solution {

    public int[][] flipAndInvertImage(int[][] image) {
        int m = image.length, n = image[0].length;
        for (int i = 0; i < m; i++) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                if (image[i][l] == image[i][r]) {
                    image[i][l] = image[i][r] ^= 1;
                }
            }
            if (n % 2 != 0) image[i][n / 2] ^= 1;
        }
        return image;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().flipAndInvertImage(new int[][]{
            {1,1,0}, {1,0,1}, {0,0,0}
        }), new int[][]{
            {1,0,0}, {0,1,0}, {1,1,1}
        });

        assert Arrays.deepEquals(new Solution().flipAndInvertImage(new int[][]{
            {1,1,0,0}, {1,0,0,1}, {0,1,1,1}, {1,0,1,0}
        }), new int[][]{
            {1,1,0,0}, {0,1,1,0}, {0,0,0,1}, {1,0,1,0}
        });
    }

}
