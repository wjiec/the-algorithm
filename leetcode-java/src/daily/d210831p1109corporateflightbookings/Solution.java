package daily.d210831p1109corporateflightbookings;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1109. Corporate Flight Bookings
 *
 * https://leetcode-cn.com/problems/corporate-flight-bookings/
 *
 * There are n flights that are labeled from 1 to n.
 *
 * You are given an array of flight bookings bookings, where bookings[i] = [firsti, lasti, seatsi]
 * represents a booking for flights firsti through lasti (inclusive) with seatsi seats reserved
 * for each flight in the range.
 *
 * Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.
 */

public class Solution {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        Arrays.sort(bookings, Comparator.comparingInt(booking -> booking[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(booking -> booking[1]));

        int[] ans = new int[n];
        int v = 0, idx = 0, l = bookings.length;
        for (int i = 1; i <= n; i++) {
            for (; idx < l && bookings[idx][0] <= i; idx++) {
                v += bookings[idx][2];
                queue.add(bookings[idx]);
            }

            ans[i - 1] = v;
            while (!queue.isEmpty() && queue.peek()[1] <= i) {
                v -= queue.remove()[2];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().corpFlightBookings(new int[][]{
            {1,2,10}, {2,3,20}, {2,5,25}
        }, 5), new int[]{10,55,45,25,25});
        assert Checker.check(new Solution().corpFlightBookings(new int[][]{
            {1,2,10}, {2,2,15}
        }, 2), new int[]{10,25});
    }

}
