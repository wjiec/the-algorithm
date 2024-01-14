package problem.p2528maximizetheminimumpoweredcity;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 2528. Maximize the Minimum Powered City
 *
 * https://leetcode.cn/problems/maximize-the-minimum-powered-city
 *
 * You are given a 0-indexed integer array stations of length n, where
 * stations[i] represents the number of power stations in the ith city.
 *
 * Each power station can provide power to every city in a fixed range.
 * In other words, if the range is denoted by r, then a power station at
 * city i can provide power to all cities j such that |i - j| <= r and 0 <= i, j <= n - 1.
 *
 * Note that |x| denotes absolute value. For example, |7 - 5| = 2 and |3 - 10| = 7.
 * The power of a city is the total number of power stations it is being provided power from.
 *
 * The government has sanctioned building k more power stations, each of which can be built
 * in any city, and have the same range as the pre-existing ones.
 *
 * Given the two integers r and k, return the maximum possible minimum power of a city, if
 * the additional power stations are built optimally.
 *
 * Note that you can build the k power stations in multiple cities.
 */

@SuppressWarnings("AssertWithSideEffects")
public class Solution {

    public long maxPower(int[] stations, int r, int k) {
        for (int i = 0; i < r; i++) preR += stations[i];

        long ans = 0, left = 0, right = (long) 10e10;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (check(stations, r, k, mid)) {
                ans = mid; left = mid + 1;
            } else right = mid - 1;
        }
        return ans;
    }

    private long preR = 0;

    private boolean check(int[] s, int r, long k, long min) {
        long curr = preR; int n = s.length;
        Queue<long[]> queue = new ArrayDeque<>();
        for (int c = 0; c < n; c++) {
            int right = c + r;
            curr += right < n ? s[right] : 0;
            if (curr < min) {
                long diff = min - curr;

                curr += diff;
                queue.add(new long[]{right, diff});
                if ((k -= diff) < 0) return false;
            }

            if (c - r >= 0) {
                curr -= s[c - r];
                if (!queue.isEmpty() && queue.peek()[0] == (c - r)) {
                    curr -= queue.remove()[1];
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().maxPower(new int[]{13,12,8,14,7}, 2, 23) == 52;
        assert new Solution().maxPower(new int[]{2,2,3}, 0, 1) == 2;
        assert new Solution().maxPower(new int[]{57,70,35,30,29,13,17,88,89,49}, 1, 90) == 138;

        assert new Solution().maxPower(new int[]{1,2,4,5,0}, 1, 2) == 5;
        assert new Solution().maxPower(new int[]{4,4,4,4}, 0, 3) == 4;
    }

}
