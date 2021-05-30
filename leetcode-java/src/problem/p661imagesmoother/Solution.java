package problem.p661imagesmoother;

import java.util.Arrays;

/**
 * 661. Image Smoother
 *
 * An image smoother is a filter of the size 3 x 3 that can be
 * applied to each cell of an image by rounding down the average
 * of the cell and the eight surrounding cells (i.e., the average
 * of the nine cells in the blue smoother).
 *
 * If one or more of the surrounding cells of a cell is not present,
 * we do not consider it in the average (i.e., the average of the four cells in the red smoother).
 */

public class Solution {

    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = avg(img, i, j, m, n);
            }
        }

        return ans;
    }

    private int avg(int[][] img, int x, int y, int m, int n) {
        int sum = 0, cnt = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x + i >= 0 && x + i < m && y + j >= 0 && y + j < n) {
                    cnt += 1;
                    sum += img[x + i][y + j];
                }
            }
        }
        return sum / cnt;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().imageSmoother(new int[][]{{1,1,1},{1,0,1},{1,1,1}}),
            new int[][]{{0,0,0},{0,0,0},{0,0,0}});
        assert Arrays.deepEquals(new Solution().imageSmoother(new int[][]{{100,200,100},{200,50,200},{100,200,100}}),
            new int[][]{{137,141,137},{141,138,141},{137,141,137}});
    }

}
