package problem.p1870minimumspeedtoarriveontime;

/**
 * 1870. Minimum Speed to Arrive on Time
 *
 * https://leetcode.cn/problems/minimum-speed-to-arrive-on-time/
 *
 * You are given a floating-point number hour, representing the amount of time you have to reach the office.
 * To commute to the office, you must take n trains in sequential order.
 * You are also given an integer array dist of length n, where dist[i]
 * describes the distance (in kilometers) of the ith train ride.
 *
 * Each train can only depart at an integer hour, so you may need to wait in between each train ride.
 *
 * For example, if the 1st train ride takes 1.5 hours, you must wait for an additional 0.5 hours
 * before you can depart on the 2nd train ride at the 2 hour mark.
 *
 * Return the minimum positive integer speed (in kilometers per hour) that all the trains must travel
 * at for you to reach the office on time, or -1 if it is impossible to be on time.
 *
 * Tests are generated such that the answer will not exceed 107 and hour
 * will have at most two digits after the decimal point.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minSpeedOnTime(int[] dist, double hour) {
        if (hour <= dist.length - 1) return -1;

        int l = 1, r = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(dist, mid, hour)) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    private boolean check(int[] dist, int s, double hour) {
        int n = dist.length, curr = 0;
        for (int i = 0; i < n - 1; i++) {
            curr += (dist[i] + s - 1) / s;
            if (curr > hour) return false;
        }
        return curr + (double) dist[n - 1] / s <= hour;
    }

    public static void main(String[] args) {
        assert new Solution().minSpeedOnTime(new int[]{1,1}, 1.0) == -1;
        assert new Solution().minSpeedOnTime(new int[]{1,1,100000}, 2.01) == 10000000;

        assert new Solution().minSpeedOnTime(new int[]{1,3,2}, 6.0) == 1;
        assert new Solution().minSpeedOnTime(new int[]{1,3,2}, 2.7) == 3;
        assert new Solution().minSpeedOnTime(new int[]{1,3,2}, 1.9) == -1;
    }

}
