package weekly.bw118.B;

import java.util.Arrays;

/**
 * 2943. Maximize Area of Square Hole in Grid
 *
 * https://leetcode.cn/contest/biweekly-contest-118/problems/maximize-area-of-square-hole-in-grid/
 *
 * There is a grid with n + 2 horizontal bars and m + 2 vertical bars, and initially containing 1 x 1 unit cells.
 *
 * The bars are 1-indexed.
 *
 * You are given the two integers, n and m.
 *
 * You are also given two integer arrays: hBars and vBars.
 *
 * hBars contains distinct horizontal bars in the range [2, n + 1].
 * vBars contains distinct vertical bars in the range [2, m + 1].
 * You are allowed to remove bars that satisfy any of the following conditions:
 *
 * If it is a horizontal bar, it must correspond to a value in hBars.
 * If it is a vertical bar, it must correspond to a value in vBars.
 *
 * Return an integer denoting the maximum area of a square-shaped hole
 * in the grid after removing some bars (possibly none).
 */

public class Solution {

    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int h = longestContinuous(hBars);
        int v = longestContinuous(vBars);
        int x = Math.min(h, v) + 1;
        return x * x;
    }

    private int longestContinuous(int[] array) {
        int prev = -1, curr = 0, ans = 0;
        for (var elem : array) {
            if (prev + 1 == elem) curr++;
            else curr = 1;

            prev = elem;
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
