package problem.p533lonelypixelii;

/**
 * 533. Lonely Pixel II
 *
 * https://leetcode.cn/problems/lonely-pixel-ii/
 *
 * Given an m x n picture consisting of black 'B' and white 'W' pixels
 * and an integer target, return the number of black lonely pixels.
 *
 * A black lonely pixel is a character 'B' that located at a specific position (r, c) where:
 *
 * Row r and column c both contain exactly target black pixels.
 * For all rows that have a black pixel at column c, they should be exactly the same as row r.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int findBlackPixel(char[][] picture, int target) {
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
        for (int j = 0; j < n; j++) {
            if (cols[j] == target) {
                int prev = -1, i = 0;
                for (; i < m; i++) {
                    if (picture[i][j] == 'B') {
                        if (rows[i] != target || (prev != -1 && !eqs(picture[prev], picture[i]))) break;
                        prev = i;
                    }
                }
                if (i == m) ans += cols[j];
            }
        }
        return ans;
    }

    private boolean eqs(char[] a, char[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
