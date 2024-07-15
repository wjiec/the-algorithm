package weekly.w406.C;

import java.util.Arrays;

/**
 * 3218. Minimum Cost for Cutting Cake I
 *
 * https://leetcode.cn/contest/weekly-contest-406/problems/minimum-cost-for-cutting-cake-i/
 *
 * There is an m x n cake that needs to be cut into 1 x 1 pieces.
 *
 * You are given integers m, n, and two arrays:
 *
 * horizontalCut of size m - 1, where horizontalCut[i] represents the cost to cut along the horizontal line i.
 * verticalCut of size n - 1, where verticalCut[j] represents the cost to cut along the vertical line j.
 * In one operation, you can choose any piece of cake that is not yet a 1 x 1 square and perform one of the following cuts:
 *
 * Cut along a horizontal line i at a cost of horizontalCut[i].
 * Cut along a vertical line j at a cost of verticalCut[j].
 * After the cut, the piece of cake is divided into two distinct pieces.
 *
 * The cost of a cut depends only on the initial cost of the line and does not change.
 *
 * Return the minimum total cost to cut the entire cake into 1 x 1 pieces.
 */

public class Solution {

    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut); Arrays.sort(verticalCut);

        int hCut = 1, vCut = 1, hi = m - 2, vi = n - 2, ans = 0;
        while (hi >= 0 || vi >= 0) {
            if (vi < 0 || hi >= 0 && horizontalCut[hi] > verticalCut[vi]) {
                ans += horizontalCut[hi--] * hCut; vCut++;
            } else {
                ans += verticalCut[vi--] * vCut; hCut++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
