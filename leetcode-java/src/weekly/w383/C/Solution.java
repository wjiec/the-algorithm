package weekly.w383.C;

/**
 * 3030. Find the Grid of Region Average
 *
 * https://leetcode.cn/contest/weekly-contest-383/problems/find-the-grid-of-region-average/
 *
 * You are given a 0-indexed m x n grid image which represents a grayscale image, where
 * image[i][j] represents a pixel with intensity in the range[0..255].
 *
 * You are also given a non-negative integer threshold.
 *
 * Two pixels image[a][b] and image[c][d] are said to be adjacent if |a - c| + |b - d| == 1.
 *
 * A region is a 3 x 3 subgrid where the absolute difference in intensity between any
 * two adjacent pixels is less than or equal to threshold.
 *
 * All pixels in a region belong to that region, note that a pixel can belong to multiple regions.
 *
 * You need to calculate a 0-indexed m x n grid result, where result[i][j] is the average
 * intensity of the region to which image[i][j] belongs, rounded down to the nearest integer.
 *
 * If image[i][j] belongs to multiple regions, result[i][j] is the average of the rounded
 * down average intensities of these regions, rounded down to the nearest integer.
 *
 * If image[i][j] does not belong to any region, result[i][j] is equal to image[i][j].
 *
 * Return the grid result.
 */

public class Solution {

    public int[][] resultGrid(int[][] image, int threshold) {
        int m = image.length, n = image[0].length;
        int[][] ans = new int[m][n], cnt = new int[m][n];
        for (int i = 2; i < m; i++) {
            next:
            for (int j = 2; j < n; j++) {
                // 检查左右相邻格子
                for (int x = i - 2; x <= i; x++) {
                    if (Math.abs(image[x][j - 2] - image[x][j - 1]) > threshold || Math.abs(image[x][j - 1] - image[x][j]) > threshold) {
                        continue next; // 不合法，下一个
                    }
                }

                // 检查上下相邻格子
                for (int y = j - 2; y <= j; ++y) {
                    if (Math.abs(image[i - 2][y] - image[i - 1][y]) > threshold || Math.abs(image[i - 1][y] - image[i][y]) > threshold) {
                        continue next; // 不合法，下一个
                    }
                }

                // 合法，计算 3x3 子网格的平均值
                int avg = 0;
                for (int x = i - 2; x <= i; x++) {
                    for (int y = j - 2; y <= j; y++) {
                        avg += image[x][y];
                    }
                }
                avg /= 9;

                // 更新 3x3 子网格内的 result
                for (int x = i - 2; x <= i; x++) {
                    for (int y = j - 2; y <= j; y++) {
                        ans[x][y] += avg; // 先累加，最后再求平均值
                        cnt[x][y]++;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cnt[i][j] == 0) { // (i,j) 不属于任何子网格
                    ans[i][j] = image[i][j];
                } else {
                    ans[i][j] /= cnt[i][j]; // 求平均值
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
