package problem.p853carfleet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 853. Car Fleet
 *
 * https://leetcode.cn/problems/car-fleet/
 *
 * There are n cars going to the same destination along a one-lane road. The destination is target miles away.
 *
 * You are given two integer array position and speed, both of length n, where position[i] is the position of
 * the ith car and speed[i] is the speed of the ith car (in miles per hour).
 *
 * A car can never pass another car ahead of it, but it can catch up to it and drive
 * bumper to bumper at the same speed. The faster car will slow down to match the slower car's speed.
 * The distance between these two cars is ignored (i.e., they are assumed to have the same position).
 *
 * A car fleet is some non-empty set of cars driving at the same position and same speed.
 * Note that a single car is also a car fleet.
 *
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 *
 * Return the number of car fleets that will arrive at the destination.
 */

public class Solution {

    private record Car(int pos, double time) {}

    public int carFleet(int target, int[] position, int[] speed) {
        Car[] cars = new Car[position.length];
        for (int i = 0; i < position.length; i++) {
            cars[i] = new Car(position[i], ((double) target - position[i]) / speed[i]);
        }
        Arrays.sort(cars, Comparator.comparingInt(v -> v.pos));

        int ans = 0, t = position.length - 1;
        for (; t > 0; t--) {
            if (cars[t].time < cars[t - 1].time) ans++;
            else cars[t - 1] = cars[t];
        }
        return ans + (t == 0 ? 1 : 0);
    }

    public static void main(String[] args) {
        assert new Solution().carFleet(10, new int[]{0, 4, 2}, new int[]{2,1,3}) == 1;

        assert new Solution().carFleet(12, new int[]{10,8,0,5,3}, new int[]{2,4,1,1,3}) == 3;
        assert new Solution().carFleet(10, new int[]{3}, new int[]{3}) == 1;
        assert new Solution().carFleet(100, new int[]{0,2,4}, new int[]{4,2,1}) == 1;
    }

}
