package weekly.bw100.D;

/**
 * 2594. Minimum Time to Repair Cars
 *
 * https://leetcode.cn/problems/minimum-time-to-repair-cars/
 *
 * You are given an integer array ranks representing the ranks of some mechanics.
 * ranksi is the rank of the ith mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.
 *
 * You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.
 *
 * Return the minimum time taken to repair all the cars.
 *
 * Note: All the mechanics can repair the cars simultaneously.
 */

public class Solution {

    public long repairCars(int[] ranks, int cars) {
        long l = 1, r = (long) (10e13 + 3), ans = 0;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (check(ranks, mid, cars)) {
                ans = mid; r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }

    // r * n * n = minutes
    // n = sqrt(minutes / r)
    private boolean check(int[] ranks, double minutes, int cars) {
        for (var rank : ranks) {
            cars -= (int) Math.sqrt(minutes / rank);
            if (cars <= 0) break;
        }
        return cars <= 0;
    }

    public static void main(String[] args) {
        assert new Solution().repairCars(new int[]{4,2,3,1}, 10) == 16;
        assert new Solution().repairCars(new int[]{5,1,8}, 6) == 16;
    }

}
