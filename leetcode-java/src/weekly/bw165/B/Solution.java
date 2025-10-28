package weekly.bw165.B;

import java.util.HashMap;
import java.util.Map;

/**
 * Q2. Minimum Discards to Balance Inventory
 *
 * https://leetcode.cn/contest/biweekly-contest-165/problems/minimum-discards-to-balance-inventory/
 *
 * You are given two integers w and m, and an integer array arrivals, where
 * arrivals[i] is the type of item arriving on day i (days are 1-indexed).
 *
 * Items are managed according to the following rules:
 *
 * Each arrival may be kept or discarded; an item may only be discarded on its arrival day.
 *
 * For each day i, consider the window of days [max(1, i - w + 1), i] (the w most recent days up to day i):
 *
 * For any such window, each item type may appear at most m times among kept arrivals
 * whose arrival day lies in that window.
 *
 * If keeping the arrival on day i would cause its type to appear more than m times
 * in the window, that arrival must be discarded.
 *
 * Return the minimum number of arrivals to be discarded so that every w-day
 * window contains at most m occurrences of each type.
 */

public class Solution {

    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int ans = 0;
        Map<Integer, Integer> g = new HashMap<>();
        for (int i = 0; i < arrivals.length; i++) {
            if (i >= w && arrivals[i - w] > 0) {
                g.merge(arrivals[i - w], -1, Integer::sum);
            }

            if (g.getOrDefault(arrivals[i], 0) + 1 > m) {
                ans++; arrivals[i] = -1; // 标记丢弃
            } else g.merge(arrivals[i], 1, Integer::sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minArrivalsToDiscard(new int[]{1,2,1,3,1}, 4, 2) == 0;
        assert new Solution().minArrivalsToDiscard(new int[]{9,3,5,7,6,5,2,10}, 4, 1) == 1;
    }

}
