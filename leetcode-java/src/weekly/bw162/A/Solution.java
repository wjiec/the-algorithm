package weekly.bw162.A;

/**
 * Q1. Earliest Finish Time for Land and Water Rides I
 *
 * https://leetcode.cn/contest/biweekly-contest-162/problems/earliest-finish-time-for-land-and-water-rides-i/
 *
 * You are given two categories of theme park attractions: land rides and water rides.
 *
 * Land rides
 * landStartTime[i] – the earliest time the ith land ride can be boarded.
 * landDuration[i] – how long the ith land ride lasts.
 *
 * Water rides
 * waterStartTime[j] – the earliest time the jth water ride can be boarded.
 * waterDuration[j] – how long the jth water ride lasts.
 *
 * A tourist must experience exactly one ride from each category, in either order.
 *
 * A ride may be started at its opening time or any later moment.
 * If a ride is started at time t, it finishes at time t + duration.
 *
 * Immediately after finishing one ride the tourist may board
 * the other (if it is already open) or wait until it opens.
 *
 * Return the earliest possible time at which the tourist can finish both rides.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            int lStart = landStartTime[i], lDur = landDuration[i];
            for (int j = 0; j < waterStartTime.length; j++) {
                int wStart = waterStartTime[j], wDur = waterDuration[j];
                ans = Math.min(ans, lStart + lDur + Math.max(wStart - (lStart + lDur), 0) + wDur);
            }
        }

        for (int j = 0; j < waterStartTime.length; j++) {
            int wStart = waterStartTime[j], wDur = waterDuration[j];
            for (int i = 0; i < landStartTime.length; i++) {
                int lStart = landStartTime[i], lDur = landDuration[i];

                ans = Math.min(ans, wStart + wDur + Math.max(lStart - (wStart + wDur), 0) + lDur);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
