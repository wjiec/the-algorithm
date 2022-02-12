package daily.d210509p1482minimumnumberofdaystomakembouquets;

/**
 * 1482. Minimum Number of Days to Make m Bouquets
 *
 * https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/
 *
 * Given an integer array bloomDay, an integer m and an integer k.
 *
 * We need to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
 *
 * The garden consists of n flowers, the ith flower will bloom in
 * the bloomDay[i] and then can be used in exactly one bouquet.
 *
 * Return the minimum number of days you need to wait to be able
 * to make m bouquets from the garden. If it is impossible to make m bouquets return -1.
 */

public class Solution {

    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }

        int r = Integer.MIN_VALUE, l = Integer.MAX_VALUE;
        for (var n : bloomDay) {
            l = Math.min(l, n);
            r = Math.max(r, n);
        }
        if (m * k == bloomDay.length) {
            return r;
        }

        while (l < r) {
            int v = (l + r) / 2;
            if (check(bloomDay, m, k, v)) {
                r = v;
            } else {
                l = v + 1;
            }
        }

        return l;
    }

    private boolean check(int[] days, int m, int k, int v) {
        int flower = 0, times = 0;
        for (var n : days) {
            if (n <= v) {
                flower++;
                if (flower == k) {
                    times++;
                    flower = 0;
                }
            } else {
                flower = 0;
            }
        }
        return times >= m;
    }

    public static void main(String[] args) {
        assert new Solution().minDays(new int[]{1,2,3,4,5}, 5, 1) == 5;
        assert new Solution().minDays(new int[]{1,10,3,10,2}, 3, 1) == 3;
        assert new Solution().minDays(new int[]{1,10,3,10,2}, 3, 2) == -1;
        assert new Solution().minDays(new int[]{7,7,7,7,12,7,7}, 2, 3) == 12;
        assert new Solution().minDays(new int[]{1000000000,1000000000}, 1, 1) == 1000000000;
    }

}
