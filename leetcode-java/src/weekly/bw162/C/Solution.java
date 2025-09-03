package weekly.bw162.C;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Q3. Earliest Finish Time for Land and Water Rides II
 *
 * https://leetcode.cn/contest/biweekly-contest-162/problems/earliest-finish-time-for-land-and-water-rides-ii/
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

    record Ride(int start, int end) {}

    /** @noinspection DuplicatedCode*/
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        Ride[] land = new Ride[landStartTime.length];
        for (int i = 0; i < landStartTime.length; i++) {
            land[i] = new Ride(landStartTime[i], landStartTime[i] + landDuration[i]);
        }

        Ride[] water = new Ride[waterStartTime.length];
        for (int i = 0; i < waterStartTime.length; i++) {
            water[i] = new Ride(waterStartTime[i], waterStartTime[i] + waterDuration[i]);
        }

        // 枚举游玩的每一个设施, 对于该设施的结束时刻 end = start + duration, 我们需要找到另一个
        // 设施在 start <= end 的最小 duration
        return Math.min(earliest(land, water), earliest(water, land));
    }

    // 计算先完成 a 项目, 再完成 b 项目的最短时间
    private int earliest(Ride[] a, Ride[] b) {
        int ans = Integer.MAX_VALUE;
        // 枚举完成 b 项目, 我们需要找到任意一个 a.end <= b.start, 最终就可以以 b.end 完成该任务
        Arrays.sort(b, Comparator.comparingInt(Ride::start));
        Arrays.sort(a, Comparator.comparingInt(Ride::end));

        for (int i = 0, j = -1; i < b.length; i++) {
            while (j + 1 < a.length && a[j + 1].end <= b[i].start) j++;
            if (j >= 0) ans = Math.min(ans, b[i].end);
            // 否则我们需要等待第一个 a 结束
            else ans = Math.min(ans, a[0].end - b[i].start + b[i].end);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().earliestFinishTime(new int[]{5}, new int[]{3}, new int[]{1}, new int[]{10}) == 14;

        assert new Solution().earliestFinishTime(new int[]{94,85}, new int[]{20,54}, new int[]{20,4}, new int[]{2,35}) == 114;
    }

}
