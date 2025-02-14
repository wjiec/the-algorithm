package weekly.bw149.B;

import java.util.ArrayList;
import java.util.List;

/**
 * 3439. Reschedule Meetings for Maximum Free Time I
 *
 * https://leetcode.cn/contest/biweekly-contest-149/problems/reschedule-meetings-for-maximum-free-time-i/
 *
 * You are given an integer eventTime denoting the duration of an event, where the event
 * occurs from time t = 0 to time t = eventTime.
 *
 * You are also given two integer arrays startTime and endTime, each of length n.
 * These represent the start and end time of n non-overlapping meetings, where the ith
 * meeting occurs during the time [startTime[i], endTime[i]].
 *
 * You can reschedule at most k meetings by moving their start time while maintaining
 * the same duration, to maximize the longest continuous period of free time during the event.
 *
 * The relative order of all the meetings should stay the same and they should remain non-overlapping.
 *
 * Return the maximum amount of free time possible after rearranging the meetings.
 *
 * Note that the meetings can not be rescheduled to a time outside the event.
 */

public class Solution {

    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        // 将随后 k 个会议紧挨着排在第 k + 1 个会议之前, 他们空余出来的空间就是最大的空隙
        List<int[]> meetings = new ArrayList<>(); meetings.add(new int[]{0, 0});
        for (int i = 0; i < startTime.length; i++) {
            meetings.add(new int[]{startTime[i], endTime[i]});
        }
        meetings.add(new int[]{eventTime, eventTime});

        // 所有长度等于 k 的会议都挪动到 k - 1 的右端点上
        int ans = 0, curr = 0;
        for (int i = 1; i < meetings.size(); i++) {
            if (i > k) ans = Math.max(ans, meetings.get(i)[0] - curr - meetings.get(i - k - 1)[1]);
            curr += meetings.get(i)[1] - meetings.get(i)[0];
            if (i > k) curr -= meetings.get(i - k)[1] - meetings.get(i - k)[0];
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
