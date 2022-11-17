package problem.p875kokoeatingbananas;

/**
 * 875. Koko Eating Bananas
 *
 * https://leetcode.cn/problems/koko-eating-bananas/
 *
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour,
 * she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and
 * will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for (var v : piles) max = Math.max(max, v);

        int ans = 1, l = 1, r = max;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(piles, h, mid)) {
                ans = mid; r = mid - 1;
            } else l = mid + 1;
        }

        return ans;
    }

    private boolean check(int[] piles, int h, int k) {
        for (var p : piles) {
            if ((h -= (p + k - 1) / k) < 0) break;
        }
        return h >= 0;
    }

    public static void main(String[] args) {
        assert new Solution().minEatingSpeed(new int[]{312884470}, 312884469) == 2;

        assert new Solution().minEatingSpeed(new int[]{3,6,7,11}, 8) == 4;
        assert new Solution().minEatingSpeed(new int[]{30,11,23,4,20}, 5) == 30;
        assert new Solution().minEatingSpeed(new int[]{30,11,23,4,20}, 6) == 23;
    }

}
