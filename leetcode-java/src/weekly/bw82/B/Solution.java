package weekly.bw82.B;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 6117. The Latest Time to Catch a Bus
 *
 * https://leetcode.cn/contest/biweekly-contest-82/problems/the-latest-time-to-catch-a-bus/
 *
 * You are given a 0-indexed integer array buses of length n, where buses[i] represents the departure
 * time of the ith bus. You are also given a 0-indexed integer array passengers of length m, where
 * passengers[j] represents the arrival time of the jth passenger.
 *
 * All bus departure times are unique.
 * All passenger arrival times are unique.
 *
 * You are given an integer capacity, which represents the maximum number of passengers that can get on each bus.
 *
 * The passengers will get on the next available bus. You can get on a bus that will depart at x minutes
 * if you arrive at y minutes where y <= x, and the bus is not full. Passengers with the earliest
 * arrival times get on the bus first.
 *
 * Return the latest time you may arrive at the bus station to catch a bus. You cannot arrive
 * at the same time as another passenger.
 *
 * Note: The arrays buses and passengers are not necessarily sorted.
 */

public class Solution {

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int l = passengers[0] - 1, r = Math.max(passengers[passengers.length - 1], buses[buses.length - 1]) + 1, ans = l;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (!check(buses, passengers, mid, capacity)) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }

        System.out.println(ans);
        ans = Math.min(buses[buses.length - 1], ans - 1);
        Set<Integer> set = new HashSet<>();
        for (var n : passengers) set.add(n);
        while (set.contains(ans)) ans--;
        return ans;
    }

    private boolean check(int[] bs, int[] ps, int v, int c) {
        int bi = 0, pi = 0, bl = bs.length, pl = ps.length;
        for (; bi < bl; bi++) {
            int curr = 0, ts = bs[bi];
            for (; pi < pl && (ps[pi] <= ts || v <= ts) && curr < c; pi++, curr++) {
                if ((pi == 0 || ps[pi - 1] <= v) && v <= ps[pi]) {
                    return true;
                }
            }
            if (pi == pl && v <= ts && curr != c) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().latestTimeCatchTheBus(new int[]{3}, new int[]{2}, 2) == 3;
        assert new Solution().latestTimeCatchTheBus(new int[]{3}, new int[]{2,4}, 2) == 3;
        assert new Solution().latestTimeCatchTheBus(new int[]{3}, new int[]{2,3}, 2) == 1;

        assert new Solution().latestTimeCatchTheBus(new int[]{20,30,10}, new int[]{19,13,26,4,25,11,21}, 2) == 20;
        assert new Solution().latestTimeCatchTheBus(new int[]{10,20}, new int[]{2,17,18,19}, 2) == 16;
    }

}
