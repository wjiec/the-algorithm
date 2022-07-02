package problem.p1094carpooling;

import java.util.PriorityQueue;

/**
 * 1094. Car Pooling
 *
 * https://leetcode.cn/problems/car-pooling/
 *
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it
 * cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi]
 * indicates that the ith trip has numPassengersi passengers and the locations to pick them up and
 * drop them off are fromi and toi respectively. The locations are given as the number of kilometers
 * due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 */

public class Solution {

    // [numPassengers, from, to]
    public boolean carPooling(int[][] trips, int capacity) {
        // [location, diff], 先下后上
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        for (var trip : trips) {
            pq.add(new int[]{trip[1], trip[0]});
            pq.add(new int[]{trip[2], -trip[0]});
        }

        int total = 0;
        while (!pq.isEmpty()) {
            total += pq.remove()[1];
            if (total > capacity) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().carPooling(new int[][]{{2,2,6},{2,4,7},{8,6,7}}, 11);

        assert !new Solution().carPooling(new int[][]{{2,1,5},{3,3,7}}, 4);
        assert new Solution().carPooling(new int[][]{{2,1,5},{3,3,7}}, 5);
    }

}
