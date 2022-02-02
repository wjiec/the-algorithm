package problem.p48rotateimage;

import java.util.Arrays;

/**
 * 48. Rotate Image
 *
 * https://leetcode-cn.com/problems/rotate-image/
 *
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 *
 * DO NOT allocate another 2D matrix and do the rotation.
 */

public class Solution {

    private final static int MASK = (1 << 16) - 1;

    public void rotate(int[][] matrix) {
        int l = matrix.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                matrix[i][j] += 1000;
            }
        }

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                int a = matrix[i][j] & MASK, b = matrix[l - j - 1][i] & MASK;
                matrix[i][j] = (b << 16) | a;
            }
        }

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                matrix[i][j] = (matrix[i][j] >> 16) - 1000;
            }
        }
    }

    public static void main(String[] args) {
        int[][] m1 = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        new Solution().rotate(m1);
        System.out.println(Arrays.deepToString(m1));

        new Solution().rotate(m1);
        System.out.println(Arrays.deepToString(m1));

        new Solution().rotate(m1);
        System.out.println(Arrays.deepToString(m1));

        new Solution().rotate(m1);
        System.out.println(Arrays.deepToString(m1));

        int[][] m2 = new int[][]{
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };
        new Solution().rotate(m2);
        System.out.println(Arrays.deepToString(m2));
    }

}
