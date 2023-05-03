package problem.p531lonelypixeli;

/**
 * 531. Lonely Pixel I
 *
 * https://leetcode.cn/problems/lonely-pixel-i/
 *
 * Given an m x n picture consisting of black 'B' and white 'W' pixels,
 * return the number of black lonely pixels.
 *
 * A black lonely pixel is a character 'B' that located at a specific position
 * where the same row and same column don't have any other black pixels.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int findLonelyPixel(char[][] picture) {
        int m = picture.length, n = picture[0].length;
        int[] rows = new int[m], cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    rows[i]++; cols[j]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] == 1 && cols[j] == 1 && picture[i][j] == 'B') ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
