package problem.p799champagnetower;

import common.Checker;

/**
 * 799. Champagne Tower
 *
 * https://leetcode.cn/problems/champagne-tower/
 *
 * We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses,
 * and so on until the 100th row. Each glass holds one cup of champagne.
 *
 * Then, some champagne is poured into the first glass at the top.
 * When the topmost glass is full, any excess liquid poured will fall
 * equally to the glass immediately to the left and right of it.
 *
 * When those glasses become full, any excess champagne will fall
 * equally to the left and right of those glasses, and so on.
 * (A glass at the bottom row has its excess champagne fall on the floor.)
 *
 * For example, after one cup of champagne is poured, the top most glass is full.
 * After two cups of champagne are poured, the two glasses on the second row are half full.
 * After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.
 * After four cups of champagne are poured, the third row has the middle glass half full,
 * and the two outside glasses are a quarter full, as pictured below.
 */

public class Solution {

    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] pyramid = new double[query_row + 2][query_row + 2];
        pyramid[0][0] = poured;
        for (int i = 0; i <= query_row; i++) {
            for (int j = 0; j <= i; j++) {
                double overflow = (pyramid[i][j] - 1.0) / 2.0;
                if (overflow > 0) {
                    pyramid[i + 1][j] += overflow;
                    pyramid[i + 1][j + 1] += overflow;
                }
            }
        }

        return Math.min(1.0, pyramid[query_row][query_glass]);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().champagneTower(1, 1, 1), 0.0);
        assert Checker.check(new Solution().champagneTower(2, 1, 1), 0.5);
        assert Checker.check(new Solution().champagneTower(100000009, 33, 17), 1.0);
    }

}
