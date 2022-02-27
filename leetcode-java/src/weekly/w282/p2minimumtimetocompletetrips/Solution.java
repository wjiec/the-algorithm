package weekly.w282.p2minimumtimetocompletetrips;

import java.util.Arrays;

/**
 * 6010. Minimum Time to Complete Trips
 *
 * https://leetcode-cn.com/contest/weekly-contest-282/problems/minimum-time-to-complete-trips/
 *
 * You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.
 *
 * Each bus can make multiple trips successively; that is, the next trip can start immediately after
 * completing the current trip. Also, each bus operates independently;
 *
 * that is, the trips of one bus do not influence the trips of any other bus.
 *
 * You are also given an integer totalTrips, which denotes the number of trips all buses should make in total.
 *
 * Return the minimum time required for all buses to complete at least totalTrips trips.
 */

public class Solution {

    public long minimumTime(int[] time, int totalTrips) {
        if (time.length == 1) return (long) time[0] * totalTrips;

        Arrays.sort(time);
        long l = time[0], r = (long) time[0] * totalTrips;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (check(time, totalTrips, mid)) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    private boolean check(int[] time, int total, long second) {
        long ret = 0;
        for (var n : time) {
            ret += second / n;
        }
        return ret >= total;
    }

    public static void main(String[] args) {
        assert new Solution().minimumTime(new int[]{5, 10, 10}, 9) == 25;

        assert new Solution().minimumTime(new int[]{1,2,3}, 5) == 3;
        assert new Solution().minimumTime(new int[]{2}, 1) == 2;
    }

}
