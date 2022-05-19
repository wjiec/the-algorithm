package problem.p835imageoverlap;

import common.TODO;

/**
 * 835. Image Overlap
 *
 * https://leetcode.cn/problems/image-overlap/
 *
 * You are given two images, img1 and img2, represented as binary,
 * square matrices of size n x n. A binary matrix has only 0s and 1s as values.
 *
 * We translate one image however we choose by sliding all the 1 bits left, right, up,
 * and/or down any number of units. We then place it on top of the other image.
 * We can then calculate the overlap by counting the number of positions that have a 1 in both images.
 *
 * Note also that a translation does not include any kind of rotation.
 * Any 1 bits that are translated outside of the matrix borders are erased.
 *
 * Return the largest possible overlap.
 */

public class Solution {

    @TODO(url = "https://leetcode.cn/problems/image-overlap/solution/tu-xiang-zhong-die-by-leetcode/")
    public int largestOverlap(int[][] img1, int[][] img2) {
        int ans = 0, n = img1.length;
        int[][] count = new int[2 * n + 1][2 * n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            if (img2[k][l] == 1) {
                                count[i - k + n][j - l + n] += 1;
                            }
                        }
                    }
                }
            }
        }

        for (var row : count) {
            for (var c : row) {
                if (c > ans) ans = c;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestOverlap(new int[][]{
            {0,0,0,0,1},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, new int[][]{
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {1,0,0,0,0}
        }) == 1;

        assert new Solution().largestOverlap(new int[][]{
            {1,1,0},
            {0,1,0},
            {0,1,0}
        }, new int[][]{
            {0,0,0},
            {0,1,1},
            {0,0,1}
        }) == 3;

        assert new Solution().largestOverlap(new int[][]{
            {1}
        }, new int[][]{
            {1}
        }) == 1;

        assert new Solution().largestOverlap(new int[][]{
            {0}
        }, new int[][]{
            {0}
        }) == 0;
    }

}
